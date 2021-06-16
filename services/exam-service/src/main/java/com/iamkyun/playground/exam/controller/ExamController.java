package com.iamkyun.playground.exam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExamController {

    @GetMapping
    public String hello() {
        return "Hello, Exam";
    }

}
