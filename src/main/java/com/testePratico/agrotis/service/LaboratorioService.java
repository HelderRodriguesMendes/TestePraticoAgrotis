package com.testePratico.agrotis.service;

import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Laboratorio salvar(Laboratorio laboratorio){
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio editar(Laboratorio laboratorio, Long id){
        laboratorio.setId(id);
        return laboratorioRepository.save(laboratorio);
    }

    public List<Laboratorio> listarTodos(Pageable pageable){
        Page<Laboratorio> laboratorios = laboratorioRepository.findAll(pageable);
        return laboratorios.getContent();
    }

    public Laboratorio laboratorioPorNome(String nome){
        Laboratorio laboratorio = laboratorioRepository.findByNome(nome);
        return laboratorio;
    }

    public Laboratorio laboratorioPorID(Long id){
        Optional<Laboratorio> laboratorioOptional = laboratorioRepository.findById(id);
        return laboratorioOptional.get();
    }

    public void deletar(Long id){
        laboratorioRepository.deleteById(id);
    }
}