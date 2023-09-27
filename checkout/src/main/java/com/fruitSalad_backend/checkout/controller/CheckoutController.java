package com.fruitSalad_backend.checkout.controller;

import com.fruitSalad_backend.checkout.error.AddressNotFoundException;
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
    private ICheckoutService checkoutService;

    @Autowired
    CheckoutProducer checkoutProducer;

    @PostMapping("")
    public String add(@RequestBody Address address) {
        try {
            checkoutProducer.sendMessage("Added address");
        } catch (Exception e) {
            System.out.println(e);
        }
        checkoutService.addAddress(address);
        return "Added " + address.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) throws AddressNotFoundException {
        if(checkoutService.getAddressById(id) == null) {
            throw new AddressNotFoundException();
        }

        try {
            checkoutProducer.sendMessage("Removed address");
        } catch (Exception e) {
            System.out.println(e);
        }
        checkoutService.removeAddress(id);
        return "Removed address with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public Address update(@PathVariable int id, @RequestBody Address addressData) throws AddressNotFoundException {
        Address existingAddress = checkoutService.getAddressById(id);

        if (existingAddress == null) {
            throw new AddressNotFoundException();
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
        checkoutService.updateAddress(existingAddress);
        return checkoutService.getAddressById(id);
    }

    @GetMapping("")
    public List<Address> list(){
        try {
            checkoutProducer.sendMessage("Listed all addresses");
        } catch (Exception e) {
            System.out.println(e);
        }
        return checkoutService.getAllAddresses();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable("id") int id) throws AddressNotFoundException {
        if(checkoutService.getAddressById(id) == null) {
            throw new AddressNotFoundException();
        }

        try {
            checkoutProducer.sendMessage("Got address by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return checkoutService.getAddressById(id);
    }

    @GetMapping("/customer/{customer}")
    public Address getByCustomer(@PathVariable("customer") String customer) throws AddressNotFoundException {
        if(checkoutService.getAddressByCustomer(customer) == null) {
            throw new AddressNotFoundException();
        }

        try {
            checkoutProducer.sendMessage("Got address by customer");
        } catch (Exception e) {
            System.out.println(e);
        }
        return checkoutService.getAddressByCustomer(customer);
    }
}
