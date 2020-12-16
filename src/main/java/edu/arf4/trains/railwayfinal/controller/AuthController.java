package edu.arf4.trains.railwayfinal.controller;

import edu.arf4.trains.railwayfinal.dto.RegisterUserDto;
import edu.arf4.trains.railwayfinal.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(RegisterUserDto dto, HttpServletRequest request) {

        try {
            userService.registerUser(dto);
        } catch (IllegalArgumentException e) {
            String msg = "user with email \"" + e.getMessage() + "\" already exists";
            request.setAttribute("errorMessage", msg);
            return "forward:errorPage";
        }
        return "redirect:login";
    }

    @PostMapping("/errorPage")
    public String error() {
        return "errorPage";
    }


}

