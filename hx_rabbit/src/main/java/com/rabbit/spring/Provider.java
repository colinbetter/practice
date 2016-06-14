package com.rabbit.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xing on 2016/6/9.
 */
public class Provider {
    public  static void main(String args[]){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-rabbit.xml");
        AmqpTemplate amqpTemplate=(AmqpTemplate)ctx.getBean("amqpTemplate");
        amqpTemplate.convertAndSend("task_queue_spring", new HuangXing());
    }
    public static class HuangXing{
        private long id=1;
        private String name="黄星";

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
