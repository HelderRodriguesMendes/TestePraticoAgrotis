package com.testePratico.agrotis.repository;

import com.testePratico.agrotis.model.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {
    public Laboratorio findByNome(String nome);
}
