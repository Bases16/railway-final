package edu.arf4.trains.railwayfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/auth")
public class AuthController {



    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/success")
    public String success() {
        return "success";
    }







}

