package edu.arf4.trains.railwayfinal.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRestController {

    @GetMapping("rest/user")
    public String user() {
        return "<h2>Welcome USER</h2>";
    }

    @GetMapping("rest/admin")
    public String admin() {
        return "<h2>Welcome ADMIN</h2>";
    }


}