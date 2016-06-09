package com.rabbit.hx;

import com.rabbitmq.client.*;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

/**
 * Created by xing on 2016/6/9.
 */
public class Provider {
    private static final String TASK_REPLY_QUEUE_NAME = "task_reply_queue";
    private static final String TASK_EXCHANGE_NAME = "task_exchange";
    private static final String TASK_KEY_NAME = "task_key";

    public static void main(String[] argv) throws java.io.IOException, TimeoutException {


        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.174.128");
        factory.setPassword("huangxing");
        factory.setUsername("huangxing");
        factory.setVirtualHost("/");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 指定队列持久化
        String responseQueue=channel.queueDeclare(TASK_REPLY_QUEUE_NAME, false, true, true, null).getQueue();
        String correlationId = UUID.randomUUID().toString() ;

        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .replyTo(responseQueue)
                .correlationId(correlationId)
                .build();
        String message = getMessage(argv);

        // 指定消息持久化
        channel.basicPublish(TASK_EXCHANGE_NAME, TASK_KEY_NAME,props,message.getBytes());
        System.out.println(" [provider] sent message:'" + message + "'");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume( responseQueue , consumer);

        while(true){

            QueueingConsumer.Delivery delivery = null;
            try {
                delivery = consumer.nextDelivery();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(delivery.getProperties().getCorrelationId().equals(correlationId)){
                String result = new String(delivery.getBody()) ;
                System.out.println("[provider]get message back:'"+result+"'");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                break;
            }
        }
        channel.close();
        connection.close();
    }


    private static String getMessage(String[] strings) {
        if (strings.length < 1)
            return "Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0)
            return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }
}

