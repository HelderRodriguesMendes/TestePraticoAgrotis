package com.testePratico.agrotis.service;

import com.testePratico.agrotis.exception.NotFoundException;
import com.testePratico.agrotis.exception.RegraNegocioException;
import com.testePratico.agrotis.model.Propriedade;
import com.testePratico.agrotis.repository.PropriedadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropriedadeService {
    
    @Autowired
    private PropriedadeRepository propriedadeRepository;

    public Propriedade salvar(Propriedade propriedade){
        Optional<Propriedade> lab = propriedadeRepository.findByCnpj(propriedade.getCnpj());
        if(lab.isPresent()){
            throw new RegraNegocioException("A propriedade " + propriedade.getNome() + " já está cadastrada");
        }
        return propriedadeRepository.save(propriedade);
    }

    public Propriedade editar(Propriedade propriedade, Long id){
        propriedade.setId(id);
        propriedadePorID(propriedade.getId());
        return propriedadeRepository.save(propriedade);
    }

    public List<Propriedade> listarTodas(){
        List<Propriedade> propriedades = propriedadeRepository.findAll();
        return propriedades;
    }

    public Propriedade propriedadePorNome(String nome){
        Optional<Propriedade> propriedade = propriedadeRepository.findByNome(nome);
        if(propriedade.isEmpty()){
            throw new NotFoundException("Propriedade " + nome + " não encontrada");
        }
        return propriedade.get();
    }

    public Propriedade propriedadePorCnpj(String cnpj){
        Optional<Propriedade> propriedade = propriedadeRepository.findByCnpj(cnpj);
        if(propriedade.isEmpty()){
            throw new NotFoundException("Propriedade " + cnpj + " não encontrada");
        }
        return propriedade.get();
    }

    public Propriedade propriedadePorID(Long id){
        Optional<Propriedade> propriedadeOptional = propriedadeRepository.findById(id);
        if(propriedadeOptional.isEmpty()){
            throw new NotFoundException("Propriedade " + id + " não encontrada");
        }
        return propriedadeOptional.get();
    }

    public void deletar(Long id){
        propriedadePorID(id);
        propriedadeRepository.deleteById(id);
    }
}