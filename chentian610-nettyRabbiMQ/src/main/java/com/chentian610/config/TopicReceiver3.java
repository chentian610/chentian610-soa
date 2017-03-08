package com.chentian610.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = "messageQueue")
public class TopicReceiver3 {

//    @RabbitHandler
    public void process(String message) {
        System.out.println("Topic message.error  : " + message);
    }

}
