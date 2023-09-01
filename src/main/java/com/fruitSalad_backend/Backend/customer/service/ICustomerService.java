package com.fruitSalad_backend.Backend.customer.service;

import com.fruitSalad_backend.Backend.customer.model.Customer;

import java.util.List;

public interface ICustomerService {
    public Customer registerCustomer(Customer customer);
    public Customer loginCustomer(Customer customer);
    public void removeCustomer(int id);
    public List<Customer> getAllCustomers();
    public Customer getCustomerById(int id);
/*    public CartItem addToCart(CartItem cartItem);
    public CartItem removeFromCart(int id);
    public ArrayList<CartItem> getCart();*/
}
