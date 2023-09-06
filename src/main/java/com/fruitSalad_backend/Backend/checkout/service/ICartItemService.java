package com.fruitSalad_backend.Backend.checkout.service;

import com.fruitSalad_backend.Backend.checkout.model.Address;

import java.util.List;

public interface ICartItemService {
    public Address addCartItem(Address cartItem);
    public void removeCartItem(int id);
    public List<Address> getAllCartItems();
    public Address getCartItemById(int id);
}
