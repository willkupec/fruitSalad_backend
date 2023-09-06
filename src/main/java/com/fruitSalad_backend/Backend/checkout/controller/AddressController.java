package com.fruitSalad_backend.Backend.checkout.controller;


import com.fruitSalad_backend.Backend.checkout.messaging.AddressProducer;
import com.fruitSalad_backend.Backend.checkout.model.Address;
import com.fruitSalad_backend.Backend.checkout.repository.AddressRepository;
import com.fruitSalad_backend.Backend.checkout.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
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

}
