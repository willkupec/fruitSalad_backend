package com.fruitSalad_backend.Backend.payment.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentRMQConfig {

    @Value("card")
    private String cardQueue;

    @Value("card_exchange")
    private String cardExchange;

    @Value("card_routing_key")
    private String cardRoutingKey;

    @Bean
    public Queue cardQueue(){
        return new Queue(cardQueue);
    }

    @Bean
    public TopicExchange cardExchange(){
        return new TopicExchange(cardExchange);
    }

    @Bean
    public Binding cardBinding(){
        return BindingBuilder
                .bind(cardQueue())
                .to(cardExchange())
                .with(cardRoutingKey);
    }
}