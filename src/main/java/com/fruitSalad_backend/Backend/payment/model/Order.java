package com.fruitSalad_backend.Backend.payment.model;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name="orders")
public class Order implements IOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalPrice;
    private String name, expiryDate;
    private BigInteger number;
    private int cvv;
    // @OneToOne(targetEntity = Address.class, mappedBy = "customer")
    private String customer;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigInteger getNumber() {
        return number;
    }

    @Override
    public void setNumber(BigInteger number) {
        this.number = number;
    }

    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public int getCVV() {
        return cvv;
    }

    @Override
    public void setCVV(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\nname: "
                + name + "\nnumber: " + number +
                "\nexpiryDate: " + expiryDate + "\nCVV: " + cvv + "\ncustomer: " + customer + "\norderItems: " + orderItems.toString();
    }

    }
