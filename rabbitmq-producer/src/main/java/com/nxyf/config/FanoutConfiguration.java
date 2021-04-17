package com.nxyf.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FanoutConfiguration
 * @Description 1.通过配置类 绑定交换机和队列   可以在生产者端绑定也可以在消费者端绑定，消费者端绑定更好
 *              2.通过web页面进行绑定，就不用通过代码绑定
 *              3.通过注解绑定
 *
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
    public Queue fanoutSmsQueue() {
        return new Queue("sms.fanout.queue",true);
    }

    @Bean
    public Queue fanoutEmailQueue() {
        return new Queue("email.fanout.queue",true);
    }

    //完成交换机和队列绑定
    @Bean
    public Binding fanoutSmsQueueBinding() {
        return BindingBuilder.bind(fanoutSmsQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutEmailQueueBinding() {
        return BindingBuilder.bind(fanoutEmailQueue()).to(fanoutExchange());
    }
}
