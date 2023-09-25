package com.fruitSalad_backend.payment.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRMQConfig {

    @Value("payment")
    private String paymentQueue;

    @Value("orderItems")
    private String orderItemsQueue;

    @Value("payment_exchange")
    private String paymentExchange;

    @Value("orderItems_exchange")
    private String orderItemsExchange;

    @Value("payment_routing_key")
    private String paymentRoutingKey;

    @Value("orderItems_routing_key")
    private String orderItemsRoutingKey;

    @Bean
    public Queue paymentQueue(){
        return new Queue(paymentQueue);
    }

    @Bean
    public Queue orderItemsQueue(){
        return new Queue(orderItemsQueue);
    }


    @Bean
    public TopicExchange paymentExchange(){
        return new TopicExchange(paymentExchange);
    }

    @Bean
    public TopicExchange orderItemsExchange(){
        return new TopicExchange(orderItemsExchange);
    }


    @Bean
    public Binding paymentBinding(){
        return BindingBuilder
                .bind(paymentQueue())
                .to(paymentExchange())
                .with(paymentRoutingKey);
    }

    @Bean
    public Binding orderItemsBinding(){
        return BindingBuilder
                .bind(orderItemsQueue())
                .to(orderItemsExchange())
                .with(orderItemsRoutingKey);
    }
}