package com.fruitSalad_backend.Backend.checkout.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutRMQConfig {

    @Value("address")
    private String addressQueue;

    @Value("address_exchange")
    private String addressExchange;

    @Value("address_routing_key")
    private String addressRoutingKey;

    @Bean
    public Queue addressQueue(){
        return new Queue(addressQueue);
    }

    @Bean
    public TopicExchange addressExchange(){
        return new TopicExchange(addressExchange);
    }

    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(addressQueue())
                .to(addressExchange())
                .with(addressRoutingKey);
    }
}