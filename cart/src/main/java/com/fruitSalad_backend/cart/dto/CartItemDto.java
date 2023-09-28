package com.fruitSalad_backend.cart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CartItemDto implements Serializable
{
    private String title;
    private double price;
    private int quantity;
    private int cartItemId;
}
