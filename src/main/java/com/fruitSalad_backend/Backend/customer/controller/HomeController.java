package com.fruitSalad_backend.Backend.customer.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

    @GetMapping("")
    public String viewHomePage() {
        return "API Running";
    }

}