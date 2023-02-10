package com.testePratico.agrotis.service;

import com.testePratico.agrotis.exception.NotFoundException;
import com.testePratico.agrotis.model.Servico;
import com.testePratico.agrotis.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public Servico salvar(Servico servico){
        return servicoRepository.save(servico);
    }

    public Servico editar(Servico servico, Long id){
        servico.setId(id);
        servicoPorID(servico.getId());
        return servicoRepository.save(servico);
    }

    public List<Servico> listarTodos(){
        List<Servico> servicos = servicoRepository.findAll();
        return servicos;
    }

    public Servico servicoPorNome(String nome){
        Optional<Servico> servico = servicoRepository.findByNome(nome);
        if(servico.isEmpty()){
            throw new NotFoundException("Servico " + nome + " não encontrado");
        }
        return servico.get();
    }

    public Servico servicoPorCnpj(String cnpj){
        Optional<Servico> servico = servicoRepository.findByCnpj(cnpj);
        if(servico.isEmpty()){
            throw new NotFoundException("Servico " + cnpj + " não encontrado");
        }
        return servico.get();
    }

    public Servico servicoPorID(Long id){
        Optional<Servico> servicoOptional = servicoRepository.findById(id);
        if(servicoOptional.isEmpty()){
            throw new NotFoundException("Servico " + id + " não encontrado");
        }
        return servicoOptional.get();
    }

    public void deletar(Long id){
        servicoPorID(id);
        servicoRepository.deleteById(id);
    }
}