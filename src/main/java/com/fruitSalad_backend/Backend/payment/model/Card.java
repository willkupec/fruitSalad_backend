package com.fruitSalad_backend.Backend.payment.model;

import com.fruitSalad_backend.Backend.checkout.model.IAddress;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigInteger;

@Entity
public class Card implements ICard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name, expiryDate;
    private BigInteger number;
    private int cvv;

    public Card() {

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
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigInteger getNumber() {
        return number;
    }

    @Override
    public void setNumber(BigInteger number) {
        this.number = number;
    }

    @Override
    public String getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public int getCVV() {
        return cvv;
    }

    @Override
    public void setCVV(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\nname: "
                + name + "\nnumber: " + number +
                "\nexpiryDate: " + expiryDate + "\nCVV: " + cvv;
    }
    }
