package com.fruitSalad_backend.Backend.cartItem.model;

import jakarta.persistence.*;

@Entity
@Table(name="cart_items")
public class CartItem implements ICartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private String src;

    public CartItem() {

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
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getSrc() {
        return src;
    }

    @Override
    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\ntitle: " + title + "\nprice: " + price + "\nsrc: " + src;
    }
}
