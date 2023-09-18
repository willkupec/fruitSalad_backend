package com.fruitSalad_backend.checkout.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CheckoutProducer {

    @Value("address_exchange")
    private String exchange;

    @Value("address_routing_key")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public CheckoutProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
