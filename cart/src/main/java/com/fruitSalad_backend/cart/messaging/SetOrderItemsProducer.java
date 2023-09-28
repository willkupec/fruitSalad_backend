package com.fruitSalad_backend.cart.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.cart.dto.CartItemDto;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@Getter
@Setter
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
        ObjectMapper objectMapper = new ObjectMapper();
        String message = objectMapper.writeValueAsString(cartItems);

        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
