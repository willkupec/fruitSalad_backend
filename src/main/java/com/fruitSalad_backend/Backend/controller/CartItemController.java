package com.fruitSalad_backend.Backend.controller;


import com.fruitSalad_backend.Backend.dto.AuthResponseDto;
import com.fruitSalad_backend.Backend.dto.LoginDto;
import com.fruitSalad_backend.Backend.dto.RegisterDto;
import com.fruitSalad_backend.Backend.model.cartItem.CartItem;
import com.fruitSalad_backend.Backend.model.customer.Customer;
import com.fruitSalad_backend.Backend.repository.CartItemRepository;
import com.fruitSalad_backend.Backend.repository.CustomerRepository;
import com.fruitSalad_backend.Backend.security.JwtGenerator;
import com.fruitSalad_backend.Backend.service.ICartItemService;
import com.fruitSalad_backend.Backend.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public CartItem add(CartItem cartItem) {
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
