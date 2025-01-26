package com.example.ayakkabi.controller;

import com.example.ayakkabi.model.UyeOl;
import com.example.ayakkabi.service.UyeOlService;
import com.example.ayakkabi.service.UrunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AnasayfaController {

    private static final Logger logger = LoggerFactory.getLogger(AnasayfaController.class);

    @Autowired
    private UyeOlService uyeOlService;

    @Autowired
    private UrunService urunService;

    @GetMapping("/")
    public String anasayfa(Model model) {
        model.addAttribute("message", "TİREÇ'e Hoşgeldiniz!");
        return "anasayfa";
    }

    @GetMapping("/uyeol")
    public String uyeOl() {
        return "uyeol";
    }

    @PostMapping("/uyeol")
    public String uyeOlPost(@RequestParam String ad,
                            @RequestParam String soyad,
                            @RequestParam String email,
                            @RequestParam String telefon,
                            @RequestParam String sifre,
                            @RequestParam String dogumTarihi,
                            @RequestParam String cinsiyet,
                            @RequestParam boolean uyelikSozlesmesi,
                            @RequestParam(required = false) boolean adimKartSozlesmesi,
                            @RequestParam boolean kvkk) {

        try {
            UyeOl uyeOl = new UyeOl();
            uyeOl.setAd(ad);
            uyeOl.setSoyad(soyad);
            uyeOl.setEmail(email);
            uyeOl.setTelefon(telefon);
            uyeOl.setSifre(sifre);
            uyeOl.setDogumTarihi(LocalDate.parse(dogumTarihi));
            uyeOl.setCinsiyet(cinsiyet);

            uyeOlService.saveUyeOl(uyeOl);
        } catch (Exception e) {
            logger.error("Kayıt sırasında bir hata oluştu: ", e);
            return "redirect:/uyeol?error=true";
        }

        return "redirect:/basarili";
    }

    @GetMapping("/basarili")
    public String basarili() {
        return "basarili";
    }

    @GetMapping("/kadin")
    public String kadin(Model model) {
        model.addAttribute("urunler", urunService.getUrunlerByKategori("kadin"));
        return "kadin";
    }

    @GetMapping("/erkek")
    public String erkek(Model model) {
        model.addAttribute("urunler", urunService.getUrunlerByKategori("erkek"));
        return "erkek";
    }

    @GetMapping("/cocuk")
    public String cocuk(Model model) {
        model.addAttribute("urunler", urunService.getUrunlerByKategori("cocuk"));
        return "cocuk";
    }

    @GetMapping("/giris")
    public String giris(Model model) {
        return "giris";
    }

    @PostMapping("/giris")
    public String girisPost(@RequestParam String email,
                            @RequestParam String sifre,
                            RedirectAttributes redirectAttributes,
                            HttpSession session) {

        UyeOl uyeOl = uyeOlService.findByEmailAndSifre(email, sifre);
        if (uyeOl != null) {
            session.setAttribute("kullanici", uyeOl);
            return "redirect:/";
        } else {
            redirectAttributes.addAttribute("error", true);
            return "redirect:/giris";
        }
    }

    @GetMapping("/iletisim")
    public String iletisim(Model model) {
        return "iletisim";
    }

    @GetMapping("/cikis")
    public String cikis(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profil")
    public String profil(HttpSession session, Model model) {
    UyeOl kullanici = (UyeOl) session.getAttribute("kullanici");
    if (kullanici != null) {
        model.addAttribute("kullanici", kullanici);
        return "profil";
    }
    return "redirect:/giris"; // Kullanıcı giriş yapmamışsa giriş sayfasına yönlendir
}


    @GetMapping("/firsatUrunu")
    public String firsatUrunu() {
        return "firsatUrunu";
    }

    @GetMapping("/odeme")
    public String odemeSayfasi() {
        return "odeme";
    }
}



