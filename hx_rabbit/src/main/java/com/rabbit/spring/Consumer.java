package com.rabbit.spring;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;

import java.io.UnsupportedEncodingException;

/**
 * Created by xing on 2016/6/9.
 */
public class Consumer implements MessageListener{
    public void onMessage(Message message) {
        SimpleMessageListenerContainer liestener=new SimpleMessageListenerContainer();
        try {
            System.out.println(new String(message.getBody(), message.getMessageProperties().getContentEncoding() == null ?
                    "UTF-8" : message.getMessageProperties().getContentEncoding()));
            System.out.println(message.getMessageProperties());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
