package com.fruitSalad_backend.Backend.cart.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CartRMQConfig {

    @Value("cartItem")
    private String cartItemQueue;

    @Value("cartItem_exchange")
    private String cartItemExchange;

    @Value("cartItem_routing_key")
    private String cartItemRoutingKey;

    @Bean
    public Queue cartItemQueue(){
        return new Queue(cartItemQueue);
    }

    @Bean
    public TopicExchange cartitemExchange(){
        return new TopicExchange(cartItemExchange);
    }

    @Bean
    public Binding cartItemBinding(){
        return BindingBuilder
                .bind(cartItemQueue())
                .to(cartitemExchange())
                .with(cartItemRoutingKey);
    }
}