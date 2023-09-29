package com.fruitSalad_backend.checkout.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="addresses")
public class Address implements IAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String customer;
    private String name, address, addressEtc, city, country;
    private int zipCode;

    @Override
    public String toString() {
        return "\nid: " + id + "\nname: "
                + name + "\naddress: " + address +
                "\naddressEtc: " + addressEtc + "\ncity: " + city
                + "\ncountry: " + country + "\nzipCode: " + zipCode
                 + "\ncustomer: " + customer;
    }
}
