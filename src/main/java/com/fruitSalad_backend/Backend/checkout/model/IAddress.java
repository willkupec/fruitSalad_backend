package com.fruitSalad_backend.Backend.checkout.model;

public interface IAddress {
    public int getId();
    public void setId(int id);
    public String getName();
    public void setName(String name);
    public String getAddress();
    public void setAddress(String address);
    public String getAddressEtc();
    public void setAddressEtc(String addressEtc);
    public String getCity();
    public void setCity(String city);
    public String getCountry();
    public void setCountry(String country);
    public int getZipCode();
    public void setZipCode(int zipCode);
    public String toString();
}
