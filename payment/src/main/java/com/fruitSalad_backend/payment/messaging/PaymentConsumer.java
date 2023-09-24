package com.fruitSalad_backend.payment.messaging;

import com.fruitSalad_backend.payment.service.IPaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentConsumer.class);

    @Autowired
    private IPaymentService paymentService;

    @RabbitListener(queues = {"payment"})
    public void consume(String message){
        LOGGER.info(String.format("Received message -> %s", message));
    }
}
