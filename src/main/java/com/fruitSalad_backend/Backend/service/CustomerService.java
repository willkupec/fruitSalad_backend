package com.fruitSalad_backend.Backend.service;

import com.fruitSalad_backend.Backend.model.cartItem.CartItem;
import com.fruitSalad_backend.Backend.model.customer.Customer;
import com.fruitSalad_backend.Backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer registerCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer loginCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void removeCustomer(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.findById(id);
    }

/*    @Override
    public CartItem addToCart(CartItem cartItem) {
        return customerRepository.save(customer);
    }

    @Override
    public CartItem removeFromCart(int id) {
        return null;
    }

    @Override
    public ArrayList<CartItem> getCart() {
        return customerRepository.findAll();
    }*/


}
