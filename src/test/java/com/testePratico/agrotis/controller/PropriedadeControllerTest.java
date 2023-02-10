package com.testePratico.agrotis.controller;

import com.testePratico.agrotis.dtos.PropriedadeDTO;
import com.testePratico.agrotis.dtos.convercoes.PropriedadeConvercoes;
import com.testePratico.agrotis.dtos.response.PropriedadeResponse;
import com.testePratico.agrotis.model.Propriedade;
import com.testePratico.agrotis.repository.PropriedadeRepository;
import com.testePratico.agrotis.service.PropriedadeService;
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
public class PropriedadeControllerTest {

    @InjectMocks
    private PropriedadeController propriedadeController;

    @Mock
    private PropriedadeService propriedadeService;

    @Mock
    private PropriedadeRepository propriedadeRepository;

    @Mock
    private PropriedadeConvercoes convercoes;

    private Propriedade propriedade1;
    private Propriedade propriedade2;
    private PropriedadeDTO propriedadeDTO;
    private Propriedade propriedade3;
    private PropriedadeResponse propriedadeResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvarTest(){
        Mockito.when(propriedadeService.salvar(Mockito.any())).thenReturn(propriedade1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(propriedadeResponse);

        ResponseEntity<PropriedadeResponse> response = propriedadeController.salvar(propriedadeDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PropriedadeResponse.class, response.getBody().getClass());
    }

    @Test
    public void editarTest(){
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(propriedadeResponse);
        Mockito.when(propriedadeService.editar(propriedade1, propriedade1.getId())).thenReturn(propriedade1);

        ResponseEntity<PropriedadeResponse> response = propriedadeController.editar(propriedadeDTO.getId(), propriedadeDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PropriedadeResponse.class, response.getBody().getClass());
    }

    @Test
    public void listarTodosTest(){
        Mockito.when(propriedadeService.listarTodas()).thenReturn(List.of(propriedade1, propriedade2));
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(propriedadeResponse);

        ResponseEntity<List<PropriedadeResponse>> response = propriedadeController.listarTodas();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(PropriedadeResponse.class, response.getBody().get(0).getClass());
    }

    @Test
    public void propriedadePorNomeTest(){
        Mockito.when(propriedadeService.propriedadePorNome(Mockito.anyString())).thenReturn(propriedade1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(propriedadeResponse);

        ResponseEntity<PropriedadeResponse> response = propriedadeController.propriedadePorNome(propriedade1.getNome());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PropriedadeResponse.class, response.getBody().getClass());
    }

    @Test
    public void propriedadePorIDTest(){
        Mockito.when(propriedadeService.propriedadePorID(Mockito.anyLong())).thenReturn(propriedade1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(propriedadeResponse);

        ResponseEntity<PropriedadeResponse> response = propriedadeController.propriedadePorID(propriedade1.getId());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(PropriedadeResponse.class, response.getBody().getClass());
    }

    @Test
    public void deletarTest(){
        Mockito.doNothing().when(propriedadeService).deletar(Mockito.anyLong());
        ResponseEntity<Void> response = propriedadeController.deletar(1L);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(propriedadeService, times(1)).deletar(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void iniciarObjetos(){
        propriedadeDTO = new PropriedadeDTO(1L, "teste11", "16.602.343/0001-68");
        propriedadeResponse = new PropriedadeResponse(1L, "teste1", "17.576.196/0001-60");
        propriedade1 = new Propriedade(1L, "teste1", "17.576.196/0001-60");
        propriedade2 = new Propriedade(2L, "teste2", "16.602.343/0001-66");
        propriedade3 = new Propriedade(2L, "teste1", "16.602.343/0001-67");
    }
}