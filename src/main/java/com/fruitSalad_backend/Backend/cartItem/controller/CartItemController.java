package com.fruitSalad_backend.Backend.cartItem.controller;


import com.fruitSalad_backend.Backend.cartItem.messaging.CartItemProducer;
import com.fruitSalad_backend.Backend.cartItem.model.CartItem;
import com.fruitSalad_backend.Backend.cartItem.repository.CartItemRepository;
import com.fruitSalad_backend.Backend.cartItem.service.ICartItemService;
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
    public String add(@RequestBody CartItem cartItem) {
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
    public List<CartItem> list(){
        try {
            cartItemProducer.sendMessage("Listed all CartItems");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getById(@PathVariable("id") int id) {
        try {
            cartItemProducer.sendMessage("Got CartItem by id");
        } catch (Exception e) {
            System.out.println(e);
        }
        return cartItemService.getCartItemById(id);
    }

}
