package com.testePratico.agrotis.controller;

import com.testePratico.agrotis.dtos.ServicoDTO;
import com.testePratico.agrotis.dtos.convercoes.ServicoConvercoes;
import com.testePratico.agrotis.dtos.response.ServicoResponse;
import com.testePratico.agrotis.model.Servico;
import com.testePratico.agrotis.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/servico")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    ServicoConvercoes convercao = new ServicoConvercoes();

    @PostMapping
    public ResponseEntity<ServicoResponse> salvar(@Valid @RequestBody ServicoDTO servicoDTO){
        Servico servico = convercao.DTOParaEntidade(servicoDTO);
        Servico servicoSalvo = servicoService.salvar(servico);
        return new ResponseEntity<>(convercao.entidadeParaResponse(servicoSalvo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponse> editar(@PathVariable Long id, @Valid @RequestBody ServicoDTO servicoDTO){
        Servico servico = convercao.DTOParaEntidade(servicoDTO);
        Servico servicoSalvo = servicoService.editar(servico, id);
        return new ResponseEntity<>(convercao.entidadeParaResponse(servicoSalvo), HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<ServicoResponse>> listarTodos(){
        List<Servico> servicos = servicoService.listarTodos();
        List<ServicoResponse> servicoResponses = servicos.stream().map(servico ->
            convercao.entidadeParaResponse(servico)).collect(Collectors.toList());
        return new ResponseEntity<>(servicoResponses, HttpStatus.OK);
    }

    @GetMapping("/servicoPorNome")
    public ResponseEntity<List<ServicoResponse>> servicoPorNome(@RequestParam String nome){
        List<Servico> servicos = servicoService.servicoPorNome(nome);
        List<ServicoResponse> responses = servicos.stream().map(servico -> convercao.entidadeParaResponse(servico)).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/servicoPorCnpj")
    public ResponseEntity<List<ServicoResponse>> servicoPorCnpj(@RequestParam String cnpj){
        List<Servico> servicos = servicoService.servicoPorCnpj(cnpj);
        List<ServicoResponse> responses = servicos.stream().map(servico -> convercao.entidadeParaResponse(servico)).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse>servicoPorID(@PathVariable Long id){
        Servico servico = servicoService.servicoPorID(id);
        return new ResponseEntity<>(convercao.entidadeParaResponse(servico), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}