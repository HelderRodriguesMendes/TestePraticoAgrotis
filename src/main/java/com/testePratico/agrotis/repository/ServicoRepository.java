package com.testePratico.agrotis.repository;

import com.testePratico.agrotis.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {
    public List<Servico> findByNome(String nome);
    public List<Servico> findByCnpj(String cnpj);
}