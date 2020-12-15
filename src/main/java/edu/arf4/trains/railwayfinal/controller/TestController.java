package edu.arf4.trains.railwayfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jsp")
public class TestController {

    @GetMapping ("/home")
    public String all() {
        return "home";
    }
}
