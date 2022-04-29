package com.learn.controller;

// https://www.youtube.com/watch?v=Z0JfmObjKRw - tutorial spring boot - done

import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/welcome")

public class WelcomeController {

    @GetMapping 
    public String welcome(){
        return "welcome to spring boot";
    }

    // @PostMapping
    // public String other (){
    //     return "another data";
    // }
}