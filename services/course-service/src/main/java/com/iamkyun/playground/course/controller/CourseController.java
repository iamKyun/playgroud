package com.iamkyun.playground.course.controller;

import com.iamkyun.playground.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public String hello() {
        return "Hello,Course";
    }

}
