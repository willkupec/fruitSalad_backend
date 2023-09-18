package com.fruitSalad_backend.checkout.controller;

import com.fruitSalad_backend.checkout.messaging.CheckoutProducer;
import com.fruitSalad_backend.checkout.model.Address;
import com.fruitSalad_backend.checkout.repository.CheckoutRepository;
import com.fruitSalad_backend.checkout.service.ICheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/checkout")
@RestController
@CrossOrigin
public class CheckoutController {

    @Autowired
    private CheckoutRepository checkoutRepository;

    @Autowired
    private ICheckoutService addressService;

    @Autowired
    CheckoutProducer checkoutProducer;

    @PostMapping("")
    public String add(@RequestBody Address address) {
        try {
            checkoutProducer.sendMessage("Added address");
        } catch (Exception e) {
            System.out.println(e);
        }
        addressService.addAddress(address);
        return "Added " + address.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        try {
            checkoutProducer.sendMessage("Removed address");
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
            checkoutProducer.sendMessage("Updated address");
        } catch (Exception e) {
            System.out.println(e);
        }
        addressService.updateAddress(existingAddress);
        return addressService.getAllAddresses();
    }

    @GetMapping("")
    public List<Address> list(){
        try {
            checkoutProducer.sendMessage("Listed all addresses");
        } catch (Exception e) {
            System.out.println(e);
        }
        return addressService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable("id") int id) {
        try {
            checkoutProducer.sendMessage("Got address by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return addressService.getAddressById(id);
    }

    @GetMapping("/customer/{customer}")
    public Address getByCustomer(@PathVariable("customer") String customer) {
        try {
            checkoutProducer.sendMessage("Got address by customer");
        } catch (Exception e) {
            System.out.println(e);
        }
        return addressService.getAddressByCustomer(customer);
    }
}
