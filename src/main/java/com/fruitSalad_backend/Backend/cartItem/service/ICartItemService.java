package com.fruitSalad_backend.Backend.cartItem.service;

import com.fruitSalad_backend.Backend.cartItem.model.CartItem;

import java.util.List;

public interface ICartItemService {
    public CartItem addCartItem(CartItem cartItem);
    public void removeCartItem(int id);
    public List<CartItem> getAllCartItems();
    public CartItem getCartItemById(int id);
}
