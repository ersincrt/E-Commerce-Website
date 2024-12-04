package com.example.ayakkabi.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SepetimController {

    @GetMapping("/sepetim")
    public String sepetim(HttpSession session, Model model) {
        // Kullanıcı oturum açmış mı kontrol et
        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (isLoggedIn == null || !isLoggedIn) {
            model.addAttribute("isLoggedIn", false);
        } else {
            model.addAttribute("isLoggedIn", true);
        }

        return "sepetim";
    }
}
