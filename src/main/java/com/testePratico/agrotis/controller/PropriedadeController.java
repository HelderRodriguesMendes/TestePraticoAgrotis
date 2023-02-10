package com.testePratico.agrotis.controller;

import com.testePratico.agrotis.dtos.PropriedadeDTO;
import com.testePratico.agrotis.dtos.convercoes.PropriedadeConvercoes;
import com.testePratico.agrotis.dtos.response.PropriedadeResponse;
import com.testePratico.agrotis.model.Propriedade;
import com.testePratico.agrotis.service.PropriedadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/propriedade")
public class PropriedadeController {

    @Autowired
    private PropriedadeService propriedadeService;

    PropriedadeConvercoes convercao = new PropriedadeConvercoes();

    @PostMapping
    public ResponseEntity<PropriedadeResponse> salvar(@Valid @RequestBody PropriedadeDTO propriedadeDTO){
        Propriedade propriedade = convercao.DTOParaEntidade(propriedadeDTO);
        Propriedade propriedadeSalvo = propriedadeService.salvar(propriedade);
        return new ResponseEntity<>(convercao.entidadeParaResponse(propriedadeSalvo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropriedadeResponse> editar(@PathVariable Long id, @Valid @RequestBody PropriedadeDTO propriedadeDTO){
        Propriedade propriedade = convercao.DTOParaEntidade(propriedadeDTO);
        Propriedade propriedadeSalvo = propriedadeService.editar(propriedade, id);
        return new ResponseEntity<>(convercao.entidadeParaResponse(propriedadeSalvo), HttpStatus.OK);
    }

    @GetMapping("/listarTodas")
    public ResponseEntity<List<PropriedadeResponse>> listarTodas(){
        List<Propriedade> propriedades = propriedadeService.listarTodas();
        List<PropriedadeResponse> propriedadeResponses = propriedades.stream().map(propriedade ->
            convercao.entidadeParaResponse(propriedade)).collect(Collectors.toList());
        return new ResponseEntity<>(propriedadeResponses, HttpStatus.OK);
    }

    @GetMapping("/propriedadePorNome")
    public ResponseEntity<PropriedadeResponse> propriedadePorNome(@RequestParam String nome){
        Propriedade propriedade = propriedadeService.propriedadePorNome(nome);
        return new ResponseEntity<>(convercao.entidadeParaResponse(propriedade), HttpStatus.OK);
    }

    @GetMapping("/propriedadePorCnpj")
    public ResponseEntity<PropriedadeResponse> propriedadePorCnpj(@RequestParam String cnpj){
        Propriedade propriedade = propriedadeService.propriedadePorCnpj(cnpj);
        return new ResponseEntity<>(convercao.entidadeParaResponse(propriedade), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropriedadeResponse>propriedadePorID(@PathVariable Long id){
        Propriedade propriedade = propriedadeService.propriedadePorID(id);
        return new ResponseEntity<>(convercao.entidadeParaResponse(propriedade), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        propriedadeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}