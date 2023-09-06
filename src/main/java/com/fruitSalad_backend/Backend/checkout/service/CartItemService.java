package com.fruitSalad_backend.Backend.checkout.service;

import com.fruitSalad_backend.Backend.checkout.model.Address;
import com.fruitSalad_backend.Backend.checkout.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService implements ICartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public Address addCartItem(Address cartItem) {
        return cartItemRepository.save(cartItem);
    }

    @Override
    public void removeCartItem(int id) {
        cartItemRepository.deleteById(id);
    }

    @Override
    public List<Address> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    @Override
    public Address getCartItemById(int id) {
        return cartItemRepository.findById(id);
    }
}
