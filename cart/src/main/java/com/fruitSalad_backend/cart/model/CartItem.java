package com.fruitSalad_backend.cart.model;

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
@Table(name="cart_items")
public class CartItem implements ICartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private String src;

    @Override
    public String toString() {
        return "\nid: " + id + "\ntitle: " + title + "\nprice: " + price + "\nsrc: " + src;
    }
}
