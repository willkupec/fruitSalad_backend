package com.fruitSalad_backend.cart.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CartProducer {

    @Value("cart_exchange")
    private String exchange;

    @Value("cart_routing_key")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(CartProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public CartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message){
        try {
            LOGGER.info(String.format("Message sent -> %s", message));
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
