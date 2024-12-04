package com.example.ayakkabi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @GetMapping("/urun1")
    public String urun1() {
        return "urun1";
    }

    @GetMapping("/urun2")
    public String urun2() {
        return "urun2";
    }

    @GetMapping("/urun3")
    public String urun3() {
        return "urun3";
    }
}
