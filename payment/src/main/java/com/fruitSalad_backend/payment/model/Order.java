package com.fruitSalad_backend.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="orders")
public class Order implements IOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double totalPrice;
    private String name, expiryDate;
    private BigInteger number;
    private int cvv;
    private String customer;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Override
    public String toString() {
        return "\nid: " + id + "\nname: "
                + name + "\nnumber: " + number +
                "\nexpiryDate: " + expiryDate + "\nCVV: " + cvv + "\ncustomer: " + customer + "\norderItems: " + orderItems.toString();
    }
}
