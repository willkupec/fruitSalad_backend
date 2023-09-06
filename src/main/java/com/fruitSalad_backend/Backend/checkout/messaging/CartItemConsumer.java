package com.fruitSalad_backend.Backend.checkout.messaging;

import com.fruitSalad_backend.Backend.checkout.service.ICartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartItemConsumer.class);

    @Autowired
    private ICartItemService cartItemService;

    @RabbitListener(queues = {"cartItem"})
    public void consume(String message){

        LOGGER.info(String.format("Received message -> %s", message));
        cartItemService.getCartItemById(1);
    }
}
