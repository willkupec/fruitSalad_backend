package com.fruitSalad_backend.Backend.messaging;

import com.fruitSalad_backend.Backend.service.ICartItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductConsumer.class);

    @Autowired
    private ICartItemService productService;

    @RabbitListener(queues = {"product"})
    public void consume(String message){

        LOGGER.info(String.format("Received message -> %s", message));
        productService.getCartItemById(1);
    }
}
