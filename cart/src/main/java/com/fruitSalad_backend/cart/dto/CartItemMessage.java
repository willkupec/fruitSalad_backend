package com.fruitSalad_backend.cart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public record CartItemMessage(@JsonProperty("cartItemId") int cartItemId,
                              @JsonProperty("title") String title,
                              @JsonProperty("price") double price,
                              @JsonProperty("quantity") int quantity) implements Serializable {
}
