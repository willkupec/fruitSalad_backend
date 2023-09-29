package com.fruitSalad_backend.cart.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.cart.dto.CartItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetOrderItemsProducer {

    @Value("orderItems_exchange")
    private String exchange;

    @Value("orderItems_routing_key")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(SetOrderItemsProducer.class);

    private final RabbitTemplate rabbitTemplate;

    public SetOrderItemsProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(List<CartItemDto> cartItems) throws JsonProcessingException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String message = objectMapper.writeValueAsString(cartItems);

            LOGGER.info(String.format("Message sent -> %s", message));
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
