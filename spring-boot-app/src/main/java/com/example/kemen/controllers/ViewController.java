package com.example.kemen.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/")
    public String printWelcome() {
        return "hello";
    }
}
