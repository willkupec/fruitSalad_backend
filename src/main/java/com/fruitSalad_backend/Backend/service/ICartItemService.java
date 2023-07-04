package com.fruitSalad_backend.Backend.service;

import com.fruitSalad_backend.Backend.model.cartItem.CartItem;
import com.fruitSalad_backend.Backend.model.customer.Customer;

import java.util.List;

public interface ICartItemService {
    public CartItem addCartItem(CartItem cartItem);
    public void removeCartItem(int id);
    public List<CartItem> getAllCartItems();
    public CartItem getCartItemById(int id);
}
