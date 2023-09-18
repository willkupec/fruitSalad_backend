package com.fruitSalad_backend.checkout.service;

import com.fruitSalad_backend.checkout.model.Address;
import com.fruitSalad_backend.checkout.repository.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutService implements ICheckoutService {

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Override
    public Address addAddress(Address address) {
        return checkoutRepository.save(address);
    }

    @Override
    public void removeAddress(int id) {
        checkoutRepository.deleteById(id);
    }

    @Override
    public List<Address> updateAddress(Address address) {
        return checkoutRepository.findAll();
    }

    @Override
    public List<Address> getAllAddresses() {
        return checkoutRepository.findAll();
    }

    @Override
    public Address getAddressById(int id) {
        return checkoutRepository.findById(id);
    }

    @Override
    public Address getAddressByCustomer(String customer) {
        return checkoutRepository.findByCustomer(customer);
    }
}
