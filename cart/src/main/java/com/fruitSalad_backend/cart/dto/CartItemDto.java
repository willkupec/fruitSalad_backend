package com.fruitSalad_backend.cart.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemDto implements Serializable
{
    private String title;
    private double price;
    private int quantity;
    private int cartItemId;
}
