package com.fruitSalad_backend.Backend.payment.messaging;

import com.fruitSalad_backend.Backend.payment.service.ICardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(CardConsumer.class);

    @Autowired
    private ICardService cardService;

    @RabbitListener(queues = {"card"})
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
