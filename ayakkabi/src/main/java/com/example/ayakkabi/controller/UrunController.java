package com.example.ayakkabi.controller;

import com.example.ayakkabi.model.Urun;
import com.example.ayakkabi.service.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UrunController {

    @Autowired
    private UrunService urunService;

    @GetMapping("/urunler")
    public String getUrunler(Model model) {
        model.addAttribute("urunler", urunService.getAllUrunler());
        return "urunler";
    }

    @GetMapping("/urun/{id}")
    public String getUrunDetay(@PathVariable Long id, Model model) {
        Urun urun = urunService.getUrunById(id);
        model.addAttribute("urun", urun);
        return "urun-detay";
    }
}
