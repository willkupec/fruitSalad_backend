package com.fruitSalad_backend.Backend.checkout.model;

import jakarta.persistence.*;

@Entity
@Table(name="addresses")
public class Address implements IAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customer;
    private String name, address, addressEtc, city, country;
    private int zipCode;

    public Address() {

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
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getAddressEtc() {
        return addressEtc;
    }

    @Override
    public void setAddressEtc(String addressEtc) {
        this.addressEtc = addressEtc;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getCustomer() {
        return customer;
    }

    @Override
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public int getZipCode() {
        return zipCode;
    }

    @Override
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "\nid: " + id + "\nname: "
                + name + "\naddress: " + address +
                "\naddressEtc: " + addressEtc + "\ncity: " + city
                + "\ncountry: " + country + "\nzipCode: " + zipCode
                 + "\ncustomer: " + customer;
    }
}
