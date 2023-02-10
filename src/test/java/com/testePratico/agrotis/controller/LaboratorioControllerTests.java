package com.testePratico.agrotis.controller;

import com.testePratico.agrotis.dtos.LaboratorioDTO;
import com.testePratico.agrotis.dtos.convercoes.LaboratorioConvercoes;
import com.testePratico.agrotis.dtos.response.LaboratorioResponse;
import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.repository.LaboratorioRepository;
import com.testePratico.agrotis.service.LaboratorioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class LaboratorioControllerTests {

    @InjectMocks
    private LaboratorioController laboratorioController;

    @Mock
    private LaboratorioService laboratorioService;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    @Mock
    private LaboratorioConvercoes convercoes;

    private Laboratorio laboratorio1;
    private Laboratorio laboratorio2;
    private LaboratorioDTO laboratorioDTO;
    private Laboratorio laboratorio3;
    private LaboratorioResponse laboratorioResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvarTest(){
        Mockito.when(laboratorioService.salvar(Mockito.any())).thenReturn(laboratorio1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(laboratorioResponse);

        ResponseEntity<LaboratorioResponse> response = laboratorioController.salvar(laboratorioDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(LaboratorioResponse.class, response.getBody().getClass());
    }

    @Test
    public void editarTest(){
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(laboratorioResponse);
        Mockito.when(laboratorioService.editar(laboratorio1, laboratorio1.getId())).thenReturn(laboratorio1);

        ResponseEntity<LaboratorioResponse> response = laboratorioController.editar(laboratorioDTO.getId(), laboratorioDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(LaboratorioResponse.class, response.getBody().getClass());
    }

    @Test
    public void listarTodosTest(){
        Mockito.when(laboratorioService.listarTodos()).thenReturn(List.of(laboratorio1, laboratorio2));
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(laboratorioResponse);

        ResponseEntity<List<LaboratorioResponse>> response = laboratorioController.listarTodos();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(LaboratorioResponse.class, response.getBody().get(0).getClass());
    }

    @Test
    public void laboratorioPorNomeTest(){
        Mockito.when(laboratorioService.laboratorioPorNome(Mockito.anyString())).thenReturn(laboratorio1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(laboratorioResponse);

        ResponseEntity<LaboratorioResponse> response = laboratorioController.laboratorioPorNome(laboratorio1.getNome());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(LaboratorioResponse.class, response.getBody().getClass());
    }

    @Test
    public void laboratorioPorIDTest(){
        Mockito.when(laboratorioService.laboratorioPorID(Mockito.anyLong())).thenReturn(laboratorio1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(laboratorioResponse);

        ResponseEntity<LaboratorioResponse> response = laboratorioController.laboratorioPorID(laboratorio1.getId());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(LaboratorioResponse.class, response.getBody().getClass());
    }

    @Test
    public void deletarTest(){
        Mockito.doNothing().when(laboratorioService).deletar(Mockito.anyLong());
        ResponseEntity<Void> response = laboratorioController.deletar(1L);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(laboratorioService, times(1)).deletar(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void iniciarObjetos(){
        laboratorioDTO = new LaboratorioDTO(1L, "teste11");
        laboratorioResponse = new LaboratorioResponse(1L, "teste1");
        laboratorio1 = new Laboratorio(1L, "teste1");
        laboratorio2 = new Laboratorio(2L, "teste2");
        laboratorio3 = new Laboratorio(2L, "teste1");
    }
}