package edu.arf4.trains.railwayfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {



    @GetMapping
    public String homePage() {

        return "home";
    }


    @GetMapping("/uuSuk")
    public String hello() {

        return "uuSuk";
    }







}

