package com.fruitSalad_backend.Backend.payment.model;

import com.fruitSalad_backend.Backend.cart.model.CartItem;
import jakarta.persistence.*;

@Entity
@Table(name="order_items")
public class OrderItem implements IOrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;

    @OneToOne
    @JoinColumn(name = "cart_item_id", referencedColumnName = "id")
    private CartItem cartItem;

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
    public CartItem getCartItem() {
        return cartItem;
    }

    @Override
    public void setCartItem(CartItem cartItem) {
        this.cartItem = cartItem;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\nquantity: " + quantity + "\ncartItem: " + cartItem.toString();
    }
}
