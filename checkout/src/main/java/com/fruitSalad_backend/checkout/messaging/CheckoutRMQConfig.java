package com.fruitSalad_backend.checkout.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CheckoutRMQConfig {

    @Value("checkout")
    private String checkoutQueue;

    @Value("checkout_exchange")
    private String checkoutExchange;

    @Value("checkout_routing_key")
    private String checkoutRoutingKey;

    @Bean
    public Queue checkoutQueue(){
        return new Queue(checkoutQueue);
    }

    @Bean
    public TopicExchange checkoutExchange(){
        return new TopicExchange(checkoutExchange);
    }

    @Bean
    public Binding checkoutBinding(){
        return BindingBuilder
                .bind(checkoutQueue())
                .to(checkoutExchange())
                .with(checkoutRoutingKey);
    }
}