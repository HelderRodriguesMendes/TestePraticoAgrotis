package com.testePratico.agrotis.repository;

import com.testePratico.agrotis.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    public Optional<Servico> findByNome(String nome);
    public Optional<Servico> findByCnpj(String cnpj);
}