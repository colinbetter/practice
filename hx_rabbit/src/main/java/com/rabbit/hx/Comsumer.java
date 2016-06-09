package com.rabbit.hx;

import com.rabbitmq.client.*;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * Created by xing on 2016/6/9.
 */
public class Comsumer {
    private static final String TASK_QUEUE_NAME = "task_queue";
    private static final String TASK_EXCHANGE_NAME = "task_exchange";
    private static final String TASK_KEY_NAME = "task_key";
    public static void main(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.174.128");
        factory.setPassword("huangxing");
        factory.setUsername("huangxing");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection(Executors.newSingleThreadExecutor());
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(TASK_EXCHANGE_NAME,"direct",true);
        // 指定队列持久化
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        channel.queueBind(TASK_QUEUE_NAME,TASK_EXCHANGE_NAME,TASK_KEY_NAME);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 指定该消费者同时只接收一条消息
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 打开消息应答机制
        channel.basicConsume(TASK_QUEUE_NAME, false, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            AMQP.BasicProperties props =  delivery.getProperties() ;
            String message = new String(delivery.getBody());
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" [comsumer] Received '" + message + "'");
            doWork(message);
            System.out.println(" [comsumer] Done");
            AMQP.BasicProperties responseProps = new AMQP.BasicProperties.Builder()
                    .correlationId(props.getCorrelationId())
                    .build() ;

            //将结果返回到客户端Queue
            channel.basicPublish("", props.getReplyTo() , responseProps , ("[comsumer] get it"+java.time.LocalDateTime.now()).getBytes("UTF-8") ) ;

            //向客户端确认消息
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            System.out.println("[comsumer]返回消息完成..");
        }
    }

    private static void doWork(String task) throws InterruptedException {
        for (char ch : task.toCharArray()) {
            if (ch == '.')
                Thread.sleep(1000);
        }
    }
}
