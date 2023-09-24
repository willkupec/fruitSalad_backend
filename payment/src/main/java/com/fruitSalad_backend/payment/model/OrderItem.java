package com.fruitSalad_backend.payment.model;

import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem implements IOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private double price;
    private int quantity;
/*    @OneToOne
    @JoinColumn(e)*/
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
        return "\nid: " + id + "\ntitle: " +
                title +"\nprice: " + price + "\nquantity: "
                + quantity + "\ncartItemId: " + cartItemId;
    }
}
