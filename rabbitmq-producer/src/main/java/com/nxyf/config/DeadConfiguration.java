package com.nxyf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanoutConfiguration
 * @Description 死信队列配置  消息过期后绑定进入死信队列
 * @Author nxyf
 * @Date 2021/4/17 9:55
 * @Version 1.0
 **/
@Configuration
public class DeadConfiguration {

    //声明注册fanout交换机,持久化，不自动删除
    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange("dead_direct-order-exchange",true,false);
    }

    //声明队列  给队列指定过期时间
    @Bean
    public Queue deadDirectSmsQueue() {
        return new Queue("dead.direct.queue",true);
    }

    //完成交换机和队列绑定
    @Bean
    public Binding deadDirectSmsQueueBinding() {
        return BindingBuilder.bind(deadDirectSmsQueue()).to(deadDirectExchange()).with("dead");
    }
}
