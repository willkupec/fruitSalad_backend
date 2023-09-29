package com.fruitSalad_backend.checkout.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class CheckoutExceptionHandler {
    // used to convert Java Exception into a response code
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(AddressNotFoundException.class)
    public void handleNotFound(AddressNotFoundException ex) {
        log.error("Address Not Found");
    }
}
