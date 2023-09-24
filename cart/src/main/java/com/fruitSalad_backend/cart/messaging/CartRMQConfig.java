package com.fruitSalad_backend.cart.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartRMQConfig {

    @Value("cart")
    private String cartQueue;

    @Value("cart_exchange")
    private String cartExchange;

    @Value("cart_routing_key")
    private String cartRoutingKey;

    @Bean
    public Queue cartQueue(){
        return new Queue(cartQueue);
    }

    @Bean
    public TopicExchange cartExchange(){
        return new TopicExchange(cartExchange);
    }

    @Bean
    public Binding cartBinding(){
        return BindingBuilder
                .bind(cartQueue())
                .to(cartExchange())
                .with(cartRoutingKey);
    }
}