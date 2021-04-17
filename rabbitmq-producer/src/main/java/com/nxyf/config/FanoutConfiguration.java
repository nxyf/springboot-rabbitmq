package com.nxyf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanoutConfiguration
 * @Description 用于交换机和队列的绑定
 * @Author nxyf
 * @Date 2021/4/17 9:55
 * @Version 1.0
 **/
@Configuration
public class FanoutConfiguration {

    //声明注册fanout交换机,持久化，不自动删除
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout-order-exchange",true,false);
    }

    //声明队列 2个队列
    @Bean
    public Queue smsQueue() {
        return new Queue("sms.fanout.queue",true);
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("email.fanout.queue",true);
    }

    //完成交换机和队列绑定
    @Bean
    public Binding smsQueueBinding() {
        return BindingBuilder.bind(smsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding emailQueueBinding() {
        return BindingBuilder.bind(emailQueue()).to(fanoutExchange());
    }
}
