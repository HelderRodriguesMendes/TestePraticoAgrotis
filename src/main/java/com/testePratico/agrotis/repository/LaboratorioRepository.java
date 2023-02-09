package com.testePratico.agrotis.repository;

import com.testePratico.agrotis.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    public Optional<Laboratorio> findByNome(String nome);
}
