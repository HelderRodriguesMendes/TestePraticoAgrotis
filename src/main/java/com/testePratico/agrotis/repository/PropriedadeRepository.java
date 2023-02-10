package com.testePratico.agrotis.repository;

import com.testePratico.agrotis.model.Propriedade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PropriedadeRepository extends JpaRepository<Propriedade, Long> {
    public Optional<Propriedade> findByNome(String nome);
    public Optional<Propriedade> findByCnpj(String cnpj);
}