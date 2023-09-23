package com.fruitSalad_backend.cart.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.cart.dto.CartItemDom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AddToCartProducer {

    @Value("payment_exchange")
    private String exchange;

    @Value("payment_routing_key")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToCartProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public AddToCartProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(CartItemDom cartItemDom) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(cartItemDom);

        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
