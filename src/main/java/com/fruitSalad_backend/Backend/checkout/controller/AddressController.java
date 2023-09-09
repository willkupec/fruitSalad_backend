package com.fruitSalad_backend.Backend.checkout.controller;


import com.fruitSalad_backend.Backend.cartItem.model.CartItem;
import com.fruitSalad_backend.Backend.checkout.messaging.AddressProducer;
import com.fruitSalad_backend.Backend.checkout.model.Address;
import com.fruitSalad_backend.Backend.checkout.repository.AddressRepository;
import com.fruitSalad_backend.Backend.checkout.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/address")
@RestController
@CrossOrigin
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private IAddressService addressService;

    @Autowired
    AddressProducer addressProducer;

    @PostMapping("")
    public String add(@RequestBody Address address) {
        try {
            addressProducer.sendMessage("Added address");
        } catch (Exception e) {
            System.out.println(e);
        }
        addressService.addAddress(address);
        return "Added " + address.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        try {
            addressProducer.sendMessage("Removed address");
        } catch (Exception e) {
            System.out.println(e);
        }
        addressService.removeAddress(id);
        return "Removed address with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public List<Address> update(@PathVariable int id, @RequestBody Address addressData) throws Exception {
        Address existingAddress = addressService.getAddressById(id);

        if (existingAddress == null) {
            throw new Exception("Address Not Found");
        }

        String name = addressData.getName();
        String address = addressData.getAddress();
        String addressEtc = addressData.getAddressEtc();
        String city = addressData.getCity();
        String country = addressData.getCountry();
        String customer = addressData.getCustomer();
        int zipCode = addressData.getZipCode();

        existingAddress.setName(name);
        existingAddress.setAddress(address);
        existingAddress.setAddressEtc(addressEtc);
        existingAddress.setCity(city);
        existingAddress.setCountry(country);
        existingAddress.setCustomer(customer);
        existingAddress.setZipCode(zipCode);

        try {
            addressProducer.sendMessage("Updated address");
        } catch (Exception e) {
            System.out.println(e);
        }
        addressService.updateAddress(existingAddress);
        return addressService.getAllAddresses();
    }

    @GetMapping("")
    public List<Address> list(){
        try {
            addressProducer.sendMessage("Listed all addresses");
        } catch (Exception e) {
            System.out.println(e);
        }
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable("id") int id) {
        try {
            addressProducer.sendMessage("Got address by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return addressService.getAddressById(id);
    }

    @GetMapping("/customer/{customer}")
    public Address getByCustomer(@PathVariable("customer") String customer) {
        try {
            addressProducer.sendMessage("Got address by customer");
        } catch (Exception e) {
            System.out.println(e);
        }
        return addressService.getAddressByCustomer(customer);
    }
}
