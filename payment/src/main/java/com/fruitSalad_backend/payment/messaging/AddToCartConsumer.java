package com.fruitSalad_backend.payment.messaging;

import com.fruitSalad_backend.payment.service.IPaymentService;
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

    @RabbitListener(queues = {"addToCart"})
    public void consume(Message message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
