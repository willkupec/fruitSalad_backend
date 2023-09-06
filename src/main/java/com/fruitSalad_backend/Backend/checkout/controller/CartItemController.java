package com.fruitSalad_backend.Backend.checkout.controller;


import com.fruitSalad_backend.Backend.checkout.messaging.CartItemProducer;
import com.fruitSalad_backend.Backend.checkout.model.Address;
import com.fruitSalad_backend.Backend.checkout.repository.CartItemRepository;
import com.fruitSalad_backend.Backend.checkout.service.ICartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/cartItem")
@RestController
@CrossOrigin
public class CartItemController {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ICartItemService cartItemService;

    @Autowired
    CartItemProducer cartItemProducer;

    @PostMapping("")
    public String add(@RequestBody Address cartItem) {
        try {
            cartItemProducer.sendMessage("Added CartItem");
        } catch (Exception e) {
            System.out.println(e);
        }
        cartItemService.addCartItem(cartItem);
        return "Added " + cartItem.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        try {
            cartItemProducer.sendMessage("Removed CartItem");
        } catch (Exception e) {
            System.out.println(e);
        }
        cartItemService.removeCartItem(id);
        return "Removed CartItem with id: " + id;
    }

    @GetMapping("")
    public List<Address> list(){
        try {
            cartItemProducer.sendMessage("Listed all CartItems");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable("id") int id) {
        try {
            cartItemProducer.sendMessage("Got CartItem by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cartItemService.getCartItemById(id);
    }

}
