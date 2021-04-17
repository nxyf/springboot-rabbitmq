package com.nxyf.consumer.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @ClassName FanoutOrderReceiver
 * @Description 通过注解进行交换机和队列进行绑定
 * @Author nxyf
 * @Date 2021/4/17 10:27
 * @Version 1.0
 **/
@Slf4j
@Component
@RabbitListener(bindings =@QueueBinding(
        value = @Queue(value = "sms.topic.queue",durable = "true",autoDelete = "false"),
        exchange = @Exchange(value = "topic_order_exchange",type = ExchangeTypes.TOPIC),
        key = "#.sms.#"
))
public class TopicSmsReceiver {

    @RabbitHandler
    public void SmsReceiver(String message) {
        log.info("开始消费短信---topic----->{}", message);
    }
}
