package com.nxyf.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName TtlOrderService
 * @Description 对队列设置过期时间，过期后队列中的消息自动删除或绑定死信队列，存入死信队列
 * 队列设置过期时间与消息设置过期时间区别：队列设置过期时间，消息会存入死信队列
 * @Author nxyf
 * @Date 2021/4/17 13:46
 * @Version 1.0
 **/
@Slf4j
@Component
public class TtlOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //队列绑定过期时间
    public void createOrder() {

        //根据商品id查询库存
        //保存订单
        String orderId = UUID.randomUUID().toString();
        //将订单分发到mq
        String exchangeName = "ttl_direct-order-exchange";
        String routingKey = "ttl";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId);
        log.info("订单创建成功----topic-------->{}", orderId);
    }

    //消息绑定过期时间
    public void createTtlMessageOrder() {

        //根据商品id查询库存
        //保存订单
        String orderId = UUID.randomUUID().toString();
        //将订单分发到mq
        String exchangeName = "ttl_direct-order-exchange";
        String routingKey = "ttl.message";
        //给消息指定过期时间
        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("5000");
                message.getMessageProperties().setContentEncoding("UTF-8");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(exchangeName, routingKey, orderId,messagePostProcessor);
        log.info("订单创建成功----topic-------->{}", orderId);
    }
}
