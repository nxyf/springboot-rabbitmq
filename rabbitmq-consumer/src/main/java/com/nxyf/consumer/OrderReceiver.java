package com.nxyf.consumer;

import com.nxyf.entity.Order;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @ClassName OrderReceiver
 * @Description TODO
 * @Author nxyf
 * @Date 2021/3/13 9:59
 * @Version 1.0
 **/
@Component
public class OrderReceiver {

    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "order-queue",durable = "true"),
            exchange = @Exchange(value = "order-exchange",durable = "true",type = "topic"),
            key = {"order.#"}
    ))
    public void onOrderMessage(@Payload Order order,
                               @Headers Map<String,Object> headers,
                               Channel channel) throws IOException {

        System.out.println("消费者开始消费");
        //ack
        Long delivery = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
        channel.basicAck(delivery,false);
    }
}
