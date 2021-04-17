package com.nxyf;

import com.nxyf.entity.Order;
import com.nxyf.producer.DirectOrderService;
import com.nxyf.producer.FanoutOrderService;
import com.nxyf.producer.OrderSender;
import com.nxyf.producer.TopicOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private FanoutOrderService fanoutOrderService;

    @Autowired
    private DirectOrderService directOrderService;

    @Autowired
    private TopicOrderService topicOrderService;

    @Test
    void contextLoads() {
        Order order = new Order();
        order.setId("1");
        order.setName("测试订单");
        order.setMessageId(UUID.randomUUID().toString());
        orderSender.send(order);
    }

    @Test
    void fanoutContextLoads() {
        fanoutOrderService.createOrder();
    }

    @Test
    void directContextLoads() {
        directOrderService.createOrder();
    }

    @Test
    void topicContextLoads() {
        topicOrderService.createOrder();
    }
}

