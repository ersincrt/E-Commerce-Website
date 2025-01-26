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
        return urunRepository.findAllByOrderByIdDesc();
    }

    public List<Urun> getUrunlerByKategori(String kategori) {
        return urunRepository.findByKategoriOrderByIdDesc(kategori);
    }

    public Urun getUrunById(Long id) {
        return urunRepository.findById(id).orElse(null);
    }

    public Urun saveUrun(Urun urun) {
        return urunRepository.save(urun);
    }

    public void deleteUrun(Long id) {
        urunRepository.deleteById(id);
    }
}
