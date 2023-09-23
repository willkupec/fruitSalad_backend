package com.fruitSalad_backend.cart.messaging;

import com.fruitSalad_backend.cart.dto.CartItemMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddToCartProducer {

    @Value("cart_exchange")
    private String exchange;

    @Value("payment_routing_key")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToCartProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public AddToCartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(CartItemMessage cartItemMessage){
        LOGGER.info(String.format("Message sent -> %s", cartItemMessage));
        rabbitTemplate.convertAndSend(exchange, routingKey, cartItemMessage);
    }
}
