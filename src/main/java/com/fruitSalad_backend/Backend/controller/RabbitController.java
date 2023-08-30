package com.fruitSalad_backend.Backend.controller;


import com.fruitSalad_backend.Backend.messaging.ProductProducer;
import com.fruitSalad_backend.Backend.messaging.UpdateProductProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController {
    @Autowired
    ProductProducer productProducer;

    @Autowired
    UpdateProductProducer updateProductProducer;

    @GetMapping("/product-queue")
    public void triggerProductQueue() {
        try {
            productProducer.sendMessage("product");
        } catch (Exception e) {
        }
    }

    @GetMapping("/product-update-queue")
    public void triggerProductUpdateQueue() {
        try {
            updateProductProducer.sendMessage("update");
        } catch (Exception e) {
        }
    }
}
