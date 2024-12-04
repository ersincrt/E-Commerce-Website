package com.example.ayakkabi.service;

import com.example.ayakkabi.model.UyeOl;
import com.example.ayakkabi.repository.UyeOlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UyeOlService {

    @Autowired
    private UyeOlRepository uyeOlRepository;

    public void saveUyeOl(UyeOl uyeOl) {
        uyeOlRepository.save(uyeOl);
    }

    public UyeOl findByEmailAndSifre(String email, String sifre) {
        return uyeOlRepository.findByEmailAndSifre(email, sifre);
    }
}
