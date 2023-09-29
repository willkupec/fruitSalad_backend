package com.fruitSalad_backend.payment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="order_items")
public class OrderItem implements IOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private int quantity;
    private int cartItemId;

    @Override
    public String toString() {
        return "\nid: " + id + "\ntitle: " +
                title +"\nprice: " + price + "\nquantity: "
                + quantity + "\ncartItemId: " + cartItemId;
    }
}
