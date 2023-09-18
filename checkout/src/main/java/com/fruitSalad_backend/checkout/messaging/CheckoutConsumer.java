package com.fruitSalad_backend.checkout.messaging;

import com.fruitSalad_backend.checkout.service.ICheckoutService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckoutConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckoutConsumer.class);

    @Autowired
    private ICheckoutService addressService;

    @RabbitListener(queues = {"address"})
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
