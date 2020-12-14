package edu.arf4.trains.railwayfinal.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {


    @GetMapping
    public String home() {
        return "<h2>Welcome whoever you are</h2>";
    }

    @GetMapping("/user")
    public String user() {
        return "<h2>Welcome USER</h2>";
    }

    @GetMapping("/admin")
    public String admin() {
        return "<h2>Welcome ADMIN</h2>";
    }


}
