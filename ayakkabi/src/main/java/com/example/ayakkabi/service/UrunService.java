package com.example.ayakkabi.service;

import com.example.ayakkabi.model.Urun;
import com.example.ayakkabi.repository.UrunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UrunService {

    @Autowired
    private UrunRepository urunRepository;

    public List<Urun> getAllUrunler() {
        return urunRepository.findAll();
    }

    public List<Urun> getFilteredUrunler(String kategori, String marka, int minFiyat, int maxFiyat) {
        return urunRepository.findByKategoriAndMarkaAndFiyatBetween(kategori, marka, minFiyat, maxFiyat);
    }
}
