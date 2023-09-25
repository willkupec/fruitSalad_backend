package com.fruitSalad_backend.payment.controller;

import com.fruitSalad_backend.payment.messaging.PaymentProducer;
import com.fruitSalad_backend.payment.model.Order;
import com.fruitSalad_backend.payment.model.OrderItem;
import com.fruitSalad_backend.payment.repository.PaymentRepository;
import com.fruitSalad_backend.payment.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RequestMapping("/payment")
@RestController
@CrossOrigin
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private IPaymentService paymentService;

    @Autowired
    private PaymentProducer paymentProducer;

    @PostMapping("")
    public String add(@RequestBody Order order) {
        try {
            paymentProducer.sendMessage("Added payment");
        } catch (Exception e) {
            System.out.println(e);
        }
        order.setOrderItems(paymentService.getOrderItems());
        paymentService.placeOrder(order);
        return "Added " + order.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        try {
            paymentProducer.sendMessage("Removed payment");
        } catch (Exception e) {
            System.out.println(e);
        }
        paymentService.removeOrder(id);
        return "Removed payment with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public List<Order> update(@PathVariable int id, @RequestBody Order order) throws Exception {
        Order existingOrder = paymentService.getOrderById(id);

        if (existingOrder == null) {
            throw new Exception("Payment Not Found");
        }

        double totalPrice = order.getTotalPrice();
        String name = order.getName();
        BigInteger number = order.getNumber();
        String expiryDate = order.getExpiryDate();
        int cvv = order.getCVV();
        String customer = order.getCustomer();
        List<OrderItem> orderItems = order.getOrderItems();

        existingOrder.setTotalPrice(totalPrice);
        existingOrder.setName(name);
        existingOrder.setNumber(number);
        existingOrder.setExpiryDate(expiryDate);
        existingOrder.setCVV(cvv);
        existingOrder.setCustomer(customer);
        existingOrder.setOrderItems(orderItems);

        try {
            paymentProducer.sendMessage("Updated payment");
        } catch (Exception e) {
            System.out.println(e);
        }
        paymentService.updateOrder(existingOrder);
        return paymentService.getAllOrders();
    }

    @GetMapping("")
    public List<Order> list(){
        try {
            paymentProducer.sendMessage("Listed all payments");
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") int id) {
        try {
            paymentProducer.sendMessage("Got payment by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return paymentService.getOrderById(id);
    }
}
