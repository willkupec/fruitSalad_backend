package com.fruitSalad_backend.payment.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fruitSalad_backend.payment.model.OrderItem;
import com.fruitSalad_backend.payment.service.IPaymentService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddToCartConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddToCartConsumer.class);

    @Autowired
    private IPaymentService paymentService;

    @RabbitListener(queues = {"payment"})
    public void consume(String message) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        LOGGER.info(String.format("Received message -> %s", message));

        OrderItem orderItem = objectMapper.readValue(message, OrderItem.class);
        System.out.println("orderItem: " + orderItem);

        // add orderItem to list of OrderItems


    }
}
