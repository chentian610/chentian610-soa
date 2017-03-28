package com.chentian610.config;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//@Component
//@RabbitListener(queues = "messageQueue")
public class TopicReceiver3 {

//    @RabbitHandler
    public void process(String message) {
        System.out.println("Topic message.error  : " + message);
    }

}
