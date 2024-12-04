package com.example.ayakkabi.repository;

import com.example.ayakkabi.model.UyeOl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UyeOlRepository extends JpaRepository<UyeOl, Long> {
    UyeOl findByEmailAndSifre(String email, String sifre);
}
