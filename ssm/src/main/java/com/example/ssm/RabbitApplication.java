package com.example.ssm;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalTime;

@SpringBootApplication
@RabbitListener(queues = "foo")
@EnableScheduling
public class RabbitApplication {

    @Bean
    public Sender mySender() {
        return new Sender();
    }

    @Bean
    public Queue fooQueue() {
        return new Queue("foo");
    }

    @RabbitHandler
    public void process(@Payload String foo) {
        System.out.println(LocalTime.now() + ": " + foo);
    }



    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);

    }

}

