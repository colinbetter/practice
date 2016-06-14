package com.rabbit.spring;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by xing on 2016/6/9.
 */
public class ReplyProvider {
    public  static void main(String args[]){
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-rabbit.xml");
        AmqpTemplate amqpTemplate=(AmqpTemplate)ctx.getBean("amqpReplyTemplate");
        Object object=amqpTemplate.convertSendAndReceive("task_reply_queue_spring",new Provider.HuangXing());
        System.out.print(object);
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

        public HuangXing(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public HuangXing() {
        }

        @Override
        public String toString() {
            return "HuangXing{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
