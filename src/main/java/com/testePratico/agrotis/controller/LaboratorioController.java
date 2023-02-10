package com.testePratico.agrotis.controller;

import com.testePratico.agrotis.dtos.LaboratorioDTO;
import com.testePratico.agrotis.dtos.convercoes.LaboratorioConvercoes;
import com.testePratico.agrotis.dtos.response.LaboratorioResponse;
import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.service.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/laboratorio"
)
public class LaboratorioController {

    @Autowired
    private LaboratorioService laboratorioService;

    LaboratorioConvercoes convercao = new LaboratorioConvercoes();

    @PostMapping
    public ResponseEntity<LaboratorioResponse> salvar(@Valid @RequestBody LaboratorioDTO laboratorioDTO){
        Laboratorio laboratorio = convercao.DTOParaEntidade(laboratorioDTO);
        Laboratorio laboratorioSalvo = laboratorioService.salvar(laboratorio);
        return new ResponseEntity<>(convercao.entidadeParaResponse(laboratorioSalvo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaboratorioResponse> editar(@PathVariable Long id, @Valid @RequestBody LaboratorioDTO laboratorioDTO){
        Laboratorio laboratorio = convercao.DTOParaEntidade(laboratorioDTO);
        Laboratorio laboratorioSalvo = laboratorioService.editar(laboratorio, id);
        return new ResponseEntity<>(convercao.entidadeParaResponse(laboratorioSalvo), HttpStatus.OK);
    }

    @GetMapping("/listarTodos")
    public ResponseEntity<List<LaboratorioResponse>> listarTodos(){
        List<Laboratorio> laboratorios = laboratorioService.listarTodos();
        List<LaboratorioResponse> laboratorioResponses = laboratorios.stream().map(laboratorio ->
            convercao.entidadeParaResponse(laboratorio)).collect(Collectors.toList());
        return new ResponseEntity<>(laboratorioResponses, HttpStatus.OK);
    }

    @GetMapping("/laboratorioPorNome")
    public ResponseEntity<LaboratorioResponse> laboratorioPorNome(@RequestParam String nome){
        Laboratorio laboratorio = laboratorioService.laboratorioPorNome(nome);
        return new ResponseEntity<>(convercao.entidadeParaResponse(laboratorio), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioResponse>laboratorioPorID(@PathVariable Long id){
        Laboratorio laboratorio = laboratorioService.laboratorioPorID(id);
        return new ResponseEntity<>(convercao.entidadeParaResponse(laboratorio), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        laboratorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}