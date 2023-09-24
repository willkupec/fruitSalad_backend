package com.fruitSalad_backend.cart.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemDom implements Serializable
{
    private int cartItemId;
    private String title;
    private double price;
    private int quantity;
}
