package edu.arf4.trains.railwayfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MainController {

    @GetMapping
    public String homepage() {
        return "home";
    }

    @GetMapping
    public String mainPage() {
        return "main";
    }
}
