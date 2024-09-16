package com.ALOTs.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookingController {
    @GetMapping
    public String home(){
        return "Welcome, thank you for visiting again. Please specify the service you want :)";
    }
}
