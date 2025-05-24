package com.minhas.midias.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minhas.midias.entidade.Midia;

public interface MidiaRepository extends JpaRepository<Midia, Long> {
    List<Midia> findByVisto(boolean visto);
}
