package com.fruitSalad_backend.checkout.service;

import com.fruitSalad_backend.checkout.model.Address;

import java.util.List;

public interface ICheckoutService {
    public Address addAddress(Address address);
    public void removeAddress(int id);
    public List<Address> updateAddress(Address address);
    public List<Address> getAllAddresses();
    public Address getAddressById(int id);
    public Address getAddressByCustomer(String customer);
}
