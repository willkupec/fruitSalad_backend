package com.fruitSalad_backend.Backend.checkout.service;

import com.fruitSalad_backend.Backend.checkout.model.Address;
import com.fruitSalad_backend.Backend.checkout.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address addAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void removeAddress(int id) {
        addressRepository.deleteById(id);
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address getAddressByCustomer(String customer) {
        return addressRepository.findByCustomer(customer);
    }
}
