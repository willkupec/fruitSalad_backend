package com.fruitSalad_backend.cart.service;

import com.fruitSalad_backend.cart.model.CartItem;
import com.fruitSalad_backend.cart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public CartItem addCartItem(CartItem cartItem) {
        return cartRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(int id) {
        cartRepository.deleteById(id);
    }

    @Override
    public List<CartItem> updateCartItem(CartItem cartItem) { return cartRepository.findAll(); }

    @Override
    public List<CartItem> getAllCartItems() {
        return cartRepository.findAll();
    }

    @Override
    public CartItem getCartItemById(int id) {
        return cartRepository.findById(id);
    }
}
