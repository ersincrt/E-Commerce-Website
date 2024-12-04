package com.example.ayakkabi.controller;

import com.example.ayakkabi.model.Urun;
import com.example.ayakkabi.service.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UrunController {

    @Autowired
    private UrunService urunService;

    @GetMapping("/urunler")
    public String getUrunler(@RequestParam(required = false) String kategori,
                             @RequestParam(required = false) String marka,
                             @RequestParam(required = false) Integer minFiyat,
                             @RequestParam(required = false) Integer maxFiyat,
                             Model model) {
        List<Urun> urunler;
        if (kategori != null || marka != null || minFiyat != null || maxFiyat != null) {
            int min = (minFiyat != null) ? minFiyat : 0;
            int max = (maxFiyat != null) ? maxFiyat : Integer.MAX_VALUE;
            urunler = urunService.getFilteredUrunler(kategori, marka, min, max);
        } else {
            urunler = urunService.getAllUrunler();
        }
        model.addAttribute("urunler", urunler);
        return "urunler";
    }
}
