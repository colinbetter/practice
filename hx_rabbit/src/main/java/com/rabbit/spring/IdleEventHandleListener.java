package com.rabbit.spring;

import org.springframework.amqp.rabbit.listener.ListenerContainerIdleEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by xing on 2016/6/10.
 */
public class IdleEventHandleListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getClass());
        if(event instanceof ListenerContainerIdleEvent){
            ListenerContainerIdleEvent idleEvent=(ListenerContainerIdleEvent )event;
            System.out.println(idleEvent);
            System.out.println(""+idleEvent.getListenerId());
            System.out.println(""+idleEvent.getQueueNames());
            System.out.println(""+idleEvent.getIdleTime());
            System.out.println(""+idleEvent.getSource());
            System.out.println(""+idleEvent.getTimestamp());
        }
    }
}
