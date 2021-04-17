package com.nxyf.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Producer
 * @Description 简单模型simple
 * @Author nxyf
 * @Date 2021/3/22 20:31
 * @Version 1.0
 **/
public class Producer {

    public static void main(String[] args) {
        //所有的中间件技术都是基于tcp/ip协议基础上构建新型的协议规范，只不过rabbitmq遵循amqp
        //1.创建连接工程
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.10.14");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        connectionFactory.setVirtualHost("/");
        //2.创建连接
        Connection connection = null;
        Channel channel = null;
        try {
            connection = connectionFactory.newConnection();
            //3.通过连接获取通道channel
            channel = connection.createChannel();
            //4.通过连接创建交换机、声明队列
            /**
             * s:队列名称
             * b:是否持久化
             * b1:排他性，是否独占队列
             * b2:是否自动删除消息
             * map:携带附属参数
             */
            String queue = "queue1";
            channel.queueDeclare(queue, false, false, false, null);
            //5.准备消息内容
            String message = "hello rabbitmq simple";
            //6.发送消息到队列
            channel.basicPublish(queue, message, null, message.getBytes());
            System.out.println("发送消息结束");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            //7.关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
            //8.关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
