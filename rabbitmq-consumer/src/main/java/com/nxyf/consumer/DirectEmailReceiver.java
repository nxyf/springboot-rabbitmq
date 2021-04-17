package com.nxyf.consumer;

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
@RabbitListener(queues = {"email.direct.queue"})
public class DirectEmailReceiver {

    @RabbitHandler
    public void emailReceiver(String message) {
        log.info("开始消费邮件---direct----->{}",message);
    }
}
