package com.testePratico.agrotis.service;

import com.testePratico.agrotis.exception.NotFoundException;
import com.testePratico.agrotis.exception.RegraNegocioException;
import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.repository.LaboratorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioRepository laboratorioRepository;

    public Laboratorio salvar(Laboratorio laboratorio){
        Optional<Laboratorio> lab = laboratorioRepository.findByNome(laboratorio.getNome());
        if(lab.isPresent()){
            throw new RegraNegocioException("O Laboratotio " + laboratorio.getNome() + " já está cadastrado");
        }
        return laboratorioRepository.save(laboratorio);
    }

    public Laboratorio editar(Laboratorio laboratorio, Long id){
        laboratorio.setId(id);
        if(laboratorio.getId() == null){
           throw new RegraNegocioException("Os dados informados não estão cadastrados");
        }
        return laboratorioRepository.save(laboratorio);
    }

    public List<Laboratorio> listarTodos(){
        List<Laboratorio> laboratorios = laboratorioRepository.findAll();
        return laboratorios;
    }

    public Laboratorio laboratorioPorNome(String nome){
        Optional<Laboratorio> laboratorio = laboratorioRepository.findByNome(nome);
        if(laboratorio.isEmpty()){
            throw new NotFoundException("Laboratotio " + nome + " não encontrado");
        }
        return laboratorio.get();
    }

    public Laboratorio laboratorioPorID(Long id){
        Optional<Laboratorio> laboratorioOptional = laboratorioRepository.findById(id);
        if(laboratorioOptional.isEmpty()){
            throw new NotFoundException("Laboratotio " + id + " não encontrado");
        }
        return laboratorioOptional.get();
    }

    public void deletar(Long id){
        laboratorioPorID(id);
        laboratorioRepository.deleteById(id);
    }
}