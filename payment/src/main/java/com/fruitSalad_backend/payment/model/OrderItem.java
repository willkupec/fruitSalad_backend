package com.fruitSalad_backend.payment.model;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem implements IOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private int cartItemId;

    public OrderItem(){

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
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public int getCartItemId() {
        return cartItemId;
    }

    @Override
    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\nquantity: " + quantity;
    }
}
