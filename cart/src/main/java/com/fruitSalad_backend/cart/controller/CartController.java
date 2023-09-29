package com.fruitSalad_backend.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fruitSalad_backend.cart.dto.CartItemDto;
import com.fruitSalad_backend.cart.messaging.SetOrderItemsProducer;
import com.fruitSalad_backend.cart.messaging.CartProducer;
import com.fruitSalad_backend.cart.model.CartItem;
import com.fruitSalad_backend.cart.repository.CartRepository;
import com.fruitSalad_backend.cart.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/cart")
@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ICartService cartService;

    @Autowired
    CartProducer cartProducer;

    @Autowired
    SetOrderItemsProducer setOrderItemsProducer;

    @PostMapping("")
    public String add(@RequestBody CartItem cartItem) {
        cartService.addCartItem(cartItem);
        cartProducer.sendMessage("Added CartItem");
        return "Added " + cartItem.toString();
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") int id) {
        cartService.removeCartItem(id);
        cartProducer.sendMessage("Removed CartItem");
        return "Removed CartItem with id: " + id;
    }

    @PutMapping("/{id}")
    @Transactional
    public List<CartItem> update(@PathVariable int id, @RequestBody CartItem cartItem) throws Exception {
        CartItem existingCartItem = cartService.getCartItemById(id);

        if (existingCartItem == null) {
            throw new Exception("CartItem Not Found");
        }

        String title = cartItem.getTitle();
        double price = cartItem.getPrice();
        String src = cartItem.getSrc();
        existingCartItem.setTitle(title);
        existingCartItem.setPrice(price);
        existingCartItem.setSrc(src);

        cartService.updateCartItem(existingCartItem);
        cartProducer.sendMessage("Updated CartItem");
        return cartService.getAllCartItems();
    }

    @GetMapping("")
    public List<CartItem> list(){
        cartProducer.sendMessage("Listed all CartItems");
        return cartService.getAllCartItems();
    }

    @GetMapping("/{id}")
    public CartItem getById(@PathVariable("id") int id) {
        cartProducer.sendMessage("Got CartItem by id");
        return cartService.getCartItemById(id);
    }

    @PostMapping("/setOrderItems")
    public void setOrderItems(@RequestBody List<CartItemDto> cartItems) throws JsonProcessingException {
        setOrderItemsProducer.sendMessage(cartItems);
    }

}
