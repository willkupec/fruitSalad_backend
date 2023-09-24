package com.fruitSalad_backend.cart.messaging;

import com.fruitSalad_backend.cart.service.ICartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartConsumer.class);

    @Autowired
    private ICartService cartService;

    @RabbitListener(queues = {"cart"})
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
