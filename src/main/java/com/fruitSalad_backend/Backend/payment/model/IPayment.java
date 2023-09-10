package com.fruitSalad_backend.Backend.payment.model;

import java.math.BigInteger;

public interface IPayment {
    public int getId();
    public void setId(int id);
    public String getName();
    public void setName(String name);
    public BigInteger getNumber();
    public void setNumber(BigInteger number);
    public String getExpiryDate();
    public void setExpiryDate(String expiryDate);
    public int getCVV();
    public void setCVV(int cvv);
    public String toString();
}
