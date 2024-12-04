package com.example.ayakkabi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SearchController {

    @GetMapping("/search")
    public ModelAndView search(@RequestParam("query") String query) {
        ModelAndView modelAndView = new ModelAndView();

        // Arama sorgusunu normalize et (boşlukları kaldır ve küçük harfe çevir)
        String normalizedQuery = query.replaceAll("\\s+", "").toLowerCase();

        // Özel karakterleri de dikkate alarak karşılaştır
        if (normalizedQuery.equals("urun1") || normalizedQuery.equals("ürün1")) {
            modelAndView.setViewName("urun1");
        } else if (normalizedQuery.equals("urun2") || normalizedQuery.equals("ürün2")) {
            modelAndView.setViewName("urun2");
        } else if (normalizedQuery.equals("urun3") || normalizedQuery.equals("ürün3")) {
            modelAndView.setViewName("urun3");
        } else {
            modelAndView.setViewName("error"); // Ürün bulunamazsa hata sayfası
            modelAndView.addObject("message", "Aradığınız ürün bulunamadı.");
        }

        return modelAndView;
    }
}
