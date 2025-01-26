package com.example.ayakkabi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.ayakkabi.model.Urun;
import com.example.ayakkabi.repository.UrunRepository;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import com.example.ayakkabi.model.Admin;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UrunRepository urunRepository;

    private final String UPLOAD_DIR = "src/main/resources/static/images/";

    @GetMapping("/panel")
    public String adminPanel(Model model) {
        model.addAttribute("urunler", urunRepository.findAll());
        model.addAttribute("urunSayisi", urunRepository.count());
        return "admin/panel";
    }

    @PostMapping("/urun/sil/{id}")
    public String urunSil(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            urunRepository.deleteById(id);
            redirectAttributes.addFlashAttribute("mesaj", "Ürün başarıyla silindi.");
            redirectAttributes.addFlashAttribute("mesajTipi", "success");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mesaj", "Ürün silinirken bir hata oluştu.");
            redirectAttributes.addFlashAttribute("mesajTipi", "danger");
        }
        return "redirect:/admin/panel";
    }

    @GetMapping("/urun/ekle")
    public String urunEkleForm(Model model) {
        model.addAttribute("urun", new Urun());
        return "admin/urun-form";
    }

    @PostMapping("/urun/ekle")
    public String urunEkle(@ModelAttribute Urun urun, @RequestParam("resimDosya") MultipartFile file, 
                          RedirectAttributes redirectAttributes) {
        try {
            // Dosya yükleme
            if (!file.isEmpty()) {
                // Klasör kontrolü
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                byte[] bytes = file.getBytes();
                Path path = uploadPath.resolve(file.getOriginalFilename());
                Files.write(path, bytes);
                urun.setResim(file.getOriginalFilename());
            }

            urunRepository.save(urun);
            redirectAttributes.addFlashAttribute("mesaj", "Ürün başarıyla eklendi.");
            redirectAttributes.addFlashAttribute("mesajTipi", "success");
            return "redirect:/urunler";
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("mesaj", "Resim yüklenirken bir hata oluştu: " + e.getMessage());
            redirectAttributes.addFlashAttribute("mesajTipi", "danger");
            return "redirect:/admin/panel";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mesaj", "Ürün eklenirken bir hata oluştu: " + e.getMessage());
            redirectAttributes.addFlashAttribute("mesajTipi", "danger");
            return "redirect:/admin/panel";
        }
    }

    @GetMapping("/urun/duzenle/{id}")
    public String urunDuzenleForm(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("urun", urunRepository.findById(id).orElseThrow());
            return "admin/urun-form";
        } catch (Exception e) {
            return "redirect:/admin";
        }
    }

    @PostMapping("/urun/duzenle/{id}")
    public String urunDuzenle(@PathVariable Long id, @ModelAttribute Urun urun, 
                             @RequestParam("resimDosya") MultipartFile file,
                             RedirectAttributes redirectAttributes) {
        try {
            // Dosya yükleme
            if (!file.isEmpty()) {
                // Klasör kontrolü
                Path uploadPath = Paths.get(UPLOAD_DIR);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }

                byte[] bytes = file.getBytes();
                Path path = uploadPath.resolve(file.getOriginalFilename());
                Files.write(path, bytes);
                urun.setResim(file.getOriginalFilename());
            }

            urunRepository.save(urun);
            redirectAttributes.addFlashAttribute("mesaj", "Ürün başarıyla güncellendi.");
            redirectAttributes.addFlashAttribute("mesajTipi", "success");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("mesaj", "Resim yüklenirken bir hata oluştu: " + e.getMessage());
            redirectAttributes.addFlashAttribute("mesajTipi", "danger");
            return "redirect:/admin";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mesaj", "Ürün güncellenirken bir hata oluştu: " + e.getMessage());
            redirectAttributes.addFlashAttribute("mesajTipi", "danger");
        }
        return "redirect:/admin";
    }

    @GetMapping("/kayit")
    public String adminKayitForm() {
        return "admin/kayit";
    }

    @PostMapping("/kayit")
    public String adminKayit(@ModelAttribute Admin admin, RedirectAttributes redirectAttributes) {
        // Admin kaydetme işlemleri
        redirectAttributes.addFlashAttribute("mesaj", "Admin başarıyla kaydedildi.");
        return "redirect:/admin/giris";
    }
} 