package com.fruitSalad_backend.Backend.controller;


import com.fruitSalad_backend.Backend.model.cartItem.CartItem;
import com.fruitSalad_backend.Backend.repository.CartItemRepository;
import com.fruitSalad_backend.Backend.service.ICartItemService;
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

    @PostMapping("")
    public CartItem add(@RequestBody CartItem cartItem) {
        return cartItemService.addCartItem(cartItem);
    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") int id) {
        cartItemService.removeCartItem(id);
    }

    @GetMapping("")
    public List<CartItem> list(){
        return cartItemService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getById(@PathVariable("id") int id) {
        return cartItemService.getCartItemById(id);
    }

}
