package com.nxyf;

import com.nxyf.producer.FanoutOrderService;
import com.nxyf.producer.OrderSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private OrderSender orderSender;

    @Autowired
    private FanoutOrderService fanoutOrderService;

    @Test
    void contextLoads() {
        /*Order order = new Order();
        order.setId("1");
        order.setName("测试订单");
        order.setMessageId(UUID.randomUUID().toString());
        orderSender.send(order);*/

        fanoutOrderService.createOrder();
    }

}
