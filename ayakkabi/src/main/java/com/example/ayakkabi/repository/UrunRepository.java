package com.example.ayakkabi.repository;

import com.example.ayakkabi.model.Urun;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrunRepository extends JpaRepository<Urun, Long> {
    List<Urun> findByKategori(String kategori);
    List<Urun> findAllByOrderByIdDesc();
    List<Urun> findByKategoriOrderByIdDesc(String kategori);
}
