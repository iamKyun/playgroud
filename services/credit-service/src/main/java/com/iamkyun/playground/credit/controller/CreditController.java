package com.iamkyun.playground.credit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreditController {

    @GetMapping
    public String hello() {
        return "Hello, Credit";
    }

}
