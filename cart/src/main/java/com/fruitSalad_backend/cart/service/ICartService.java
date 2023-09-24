package com.fruitSalad_backend.cart.service;

import com.fruitSalad_backend.cart.model.CartItem;

import java.util.List;

public interface ICartService {
    public CartItem addCartItem(CartItem cartItem);
    public void removeCartItem(int id);
    public List<CartItem> updateCartItem(CartItem cartItem);
    public List<CartItem> getAllCartItems();
    public CartItem getCartItemById(int id);
}
