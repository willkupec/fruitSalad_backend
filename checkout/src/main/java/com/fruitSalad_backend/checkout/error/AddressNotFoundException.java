package com.fruitSalad_backend.checkout.error;

public class AddressNotFoundException extends Exception {

    public AddressNotFoundException() {
        super("Address Not Found");
    }

    public AddressNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
