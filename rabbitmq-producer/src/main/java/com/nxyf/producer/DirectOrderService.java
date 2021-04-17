package com.nxyf.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName FanoutOrderService
 * @Description fanout 发布订阅模型
 * @Author nxyf
 * @Date 2021/4/17 9:43
 * @Version 1.0
 **/
@Component
@Slf4j
public class DirectOrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void createOrder() {

        //根据商品id查询库存
        //保存订单
        String orderId = UUID.randomUUID().toString();
        //将订单分发到mq
        String exchangeName = "direct-order-exchange";
        rabbitTemplate.convertAndSend(exchangeName,"sms",orderId);
        rabbitTemplate.convertAndSend(exchangeName,"email",orderId);
        log.info("订单创建成功------------>{}",orderId);
    }
}
