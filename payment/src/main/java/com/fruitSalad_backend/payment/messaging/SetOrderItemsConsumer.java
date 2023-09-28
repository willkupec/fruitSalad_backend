package com.fruitSalad_backend.payment.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.payment.model.OrderItem;
import com.fruitSalad_backend.payment.service.IPaymentService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class SetOrderItemsConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SetOrderItemsConsumer.class);

    @Autowired
    private IPaymentService paymentService;

    @RabbitListener(queues = {"orderItems"})
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info(String.format("Received message -> %s", message));

        List<OrderItem> orderItems = objectMapper.readValue(message,
                objectMapper.getTypeFactory().constructCollectionType(List.class, OrderItem.class));

        paymentService.setOrderItems(orderItems);
    }
}
