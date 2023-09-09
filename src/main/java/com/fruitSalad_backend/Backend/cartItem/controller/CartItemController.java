package com.fruitSalad_backend.Backend.cartItem.controller;


import com.fruitSalad_backend.Backend.cartItem.messaging.CartItemProducer;
import com.fruitSalad_backend.Backend.cartItem.model.CartItem;
import com.fruitSalad_backend.Backend.cartItem.repository.CartItemRepository;
import com.fruitSalad_backend.Backend.cartItem.service.ICartItemService;
import com.fruitSalad_backend.Backend.checkout.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @PutMapping("/{id}")
    @Transactional
    public List<CartItem> update(@PathVariable int id, @RequestBody CartItem cartItem) throws Exception {
        CartItem existingCartItem = cartItemService.getCartItemById(id);

        if (existingCartItem == null) {
            throw new Exception("CartItem Not Found");
        }

        String title = cartItem.getTitle();
        double price = cartItem.getPrice();
        String src = cartItem.getSrc();
        existingCartItem.setTitle(title);
        existingCartItem.setPrice(price);
        existingCartItem.setSrc(src);

        try {
            cartItemProducer.sendMessage("Updated CartItem");
        } catch (Exception e) {
            System.out.println(e);
        }

        cartItemService.updateCartItem(existingCartItem);
        return cartItemService.getAllCartItems();
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
