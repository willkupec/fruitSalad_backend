package com.fruitSalad_backend.Backend.cartItem.service;

import com.fruitSalad_backend.Backend.cartItem.model.CartItem;
import com.fruitSalad_backend.Backend.cartItem.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(int id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<CartItem> updateCartItem(CartItem cartItem) { return cartItemRepository.findAll(); }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(int id) {
        return cartItemRepository.findById(id);
    }
}
