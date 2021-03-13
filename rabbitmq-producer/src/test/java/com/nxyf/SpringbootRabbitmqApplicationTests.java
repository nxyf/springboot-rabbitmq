package com.nxyf;

import com.nxyf.entity.Order;
import com.nxyf.producer.OrderSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private OrderSender orderSender;

    @Test
    void contextLoads() {
        Order order = new Order();
        order.setId("1");
        order.setName("测试订单");
        order.setMessageId(UUID.randomUUID().toString());
        orderSender.send(order);
    }

}
