package com.fruitSalad_backend.payment.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRMQConfig {

    @Value("payment")
    private String paymentQueue;

    @Value("addToCart")
    private String addToCartQueue;

    @Value("payment_exchange")
    private String paymentExchange;

    @Value("payment_routing_key")
    private String paymentRoutingKey;

    @Bean
    public Queue paymentQueue(){
        return new Queue(paymentQueue);
    }

    @Bean
    public Queue addToCartQueue(){
        return new Queue(addToCartQueue);
    }

    @Bean
    public TopicExchange paymentExchange(){
        return new TopicExchange(paymentExchange);
    }

    @Bean
    public Binding paymentBinding(){
        return BindingBuilder
                .bind(paymentQueue())
                .to(paymentExchange())
                .with(paymentRoutingKey);
    }

    @Bean
    public Binding addToCartBinding(){
        return BindingBuilder
                .bind(addToCartQueue())
                .to(paymentExchange())
                .with(paymentRoutingKey);
    }
}