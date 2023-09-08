package com.fruitSalad_backend.Backend.checkout.service;

import com.fruitSalad_backend.Backend.checkout.model.Address;

import java.util.List;

public interface IAddressService {
    public Address addAddress(Address address);
    public void removeAddress(int id);
    public List<Address> getAllAddresses();
    public Address getAddressById(int id);
    public Address getAddressByCustomer(String customer);
}
