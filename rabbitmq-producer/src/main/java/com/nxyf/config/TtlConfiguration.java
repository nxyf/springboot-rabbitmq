package com.nxyf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName FanoutConfiguration
 * @Description 1.通过配置类 绑定交换机和队列   可以在生产者端绑定也可以在消费者端绑定，消费者端绑定更好
 *              2.通过web页面进行绑定，就不用通过代码绑定
 *              3.通过注解绑定
 * @Author nxyf
 * @Date 2021/4/17 9:55
 * @Version 1.0
 **/
@Configuration
public class TtlConfiguration {

    //声明注册fanout交换机,持久化，不自动删除
    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange("ttl_direct-order-exchange",true,false);
    }

    //声明队列  给队列指定过期时间
    @Bean
    public Queue ttlDirectSmsQueue() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 5000);
        args.put("x-max-length", 5);
        //设置消息过期后进入死信队列
        //1.绑定死信交换机
        args.put("x-dead-letter-exchange", "dead_direct-order-exchange");
        //2.direct模式，绑定死信队列routingKey
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("ttl.direct.queue",true,false,false,args);
    }

    //声明队列  给消息指定过期时间
    @Bean
    public Queue ttlMessageDirectSmsQueue() {
        return new Queue("ttl.message.direct.queue",true);
    }

    //完成交换机和队列绑定
    @Bean
    public Binding ttlDirectSmsQueueBinding() {
        return BindingBuilder.bind(ttlDirectSmsQueue()).to(ttlDirectExchange()).with("ttl");
    }

    @Bean
    public Binding ttlMessageDirectSmsQueueBinding() {
        return BindingBuilder.bind(ttlMessageDirectSmsQueue()).to(ttlDirectExchange()).with("ttl.message");
    }
}
