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
        order.setOrderItems(paymentService.getOrderItems());
        paymentService.placeOrder(order);
        paymentProducer.sendMessage("Order placed");
        return "Placed order: " + order.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        paymentService.removeOrder(id);
        paymentProducer.sendMessage("Removed order");
        return "Removed order with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public List<Order> update(@PathVariable int id, @RequestBody Order order) throws Exception {
        Order existingOrder = paymentService.getOrderById(id);

        if (existingOrder == null) {
            throw new Exception("Order Not Found");
        }

        double totalPrice = order.getTotalPrice();
        String name = order.getName();
        BigInteger number = order.getNumber();
        String expiryDate = order.getExpiryDate();
        int cvv = order.getCvv();
        String customer = order.getCustomer();
        List<OrderItem> orderItems = order.getOrderItems();

        existingOrder.setTotalPrice(totalPrice);
        existingOrder.setName(name);
        existingOrder.setNumber(number);
        existingOrder.setExpiryDate(expiryDate);
        existingOrder.setCvv(cvv);
        existingOrder.setCustomer(customer);
        existingOrder.setOrderItems(orderItems);

        paymentService.updateOrder(existingOrder);
        paymentProducer.sendMessage("Updated order");
        return paymentService.getAllOrders();
    }

    @GetMapping("")
    public List<Order> list(){
        paymentProducer.sendMessage("Listed all orders");
        return paymentService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getById(@PathVariable("id") int id) {
        paymentProducer.sendMessage("Got order by id");
        return paymentService.getOrderById(id);
    }
}
