package com.fruitSalad_backend.Backend.customer.model;

import com.fruitSalad_backend.Backend.cartItem.model.CartItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer implements ICustomer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Customer() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n" +
                "firstName: " + firstName + "\n" +
                "lastName: " + lastName + "\n" +
                "email: " + email + "\n" +
                "password: " + password + "\n";
    }
}
