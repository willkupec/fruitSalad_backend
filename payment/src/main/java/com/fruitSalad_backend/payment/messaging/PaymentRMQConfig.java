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

    @Value("setCart")
    private String setCartQueue;

    @Value("payment_exchange")
    private String paymentExchange;

    @Value("setCart_exchange")
    private String setCartExchange;

    @Value("payment_routing_key")
    private String paymentRoutingKey;

    @Value("setCart_routing_key")
    private String setCartRoutingKey;

    @Bean
    public Queue paymentQueue(){
        return new Queue(paymentQueue);
    }

    @Bean
    public Queue setCartQueue(){
        return new Queue(setCartQueue);
    }


    @Bean
    public TopicExchange paymentExchange(){
        return new TopicExchange(paymentExchange);
    }

    @Bean
    public TopicExchange setCartExchange(){
        return new TopicExchange(setCartExchange);
    }


    @Bean
    public Binding paymentBinding(){
        return BindingBuilder
                .bind(paymentQueue())
                .to(paymentExchange())
                .with(paymentRoutingKey);
    }

    @Bean
    public Binding setCartBinding(){
        return BindingBuilder
                .bind(setCartQueue())
                .to(setCartExchange())
                .with(setCartRoutingKey);
    }
}