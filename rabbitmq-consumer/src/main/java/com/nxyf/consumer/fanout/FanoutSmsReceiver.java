package com.nxyf.consumer.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName FanoutOrderReceiver
 * @Description
 * @Author nxyf
 * @Date 2021/4/17 10:27
 * @Version 1.0
 **/
@Slf4j
@Component
@RabbitListener(queues = {"sms.fanout.queue"})
public class FanoutSmsReceiver {

    @RabbitHandler
    public void SmsReceiver(String message) {
        log.info("开始消费短信---fanout----->{}",message);
    }
}
