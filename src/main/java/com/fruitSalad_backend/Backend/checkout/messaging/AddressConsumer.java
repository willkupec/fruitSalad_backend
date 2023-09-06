package com.fruitSalad_backend.Backend.checkout.messaging;

import com.fruitSalad_backend.Backend.checkout.service.IAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressConsumer.class);

    @Autowired
    private IAddressService addressService;

    @RabbitListener(queues = {"address"})
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
