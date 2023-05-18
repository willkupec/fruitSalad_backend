package com.fruitSalad_backend.Backend.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

    @GetMapping("")
    public String viewHomePage() {
        return "fruitSalad API Running";
    }

}