package com.testePratico.agrotis.controller;

import com.testePratico.agrotis.dtos.LaboratorioDTO;
import com.testePratico.agrotis.dtos.PropriedadeDTO;
import com.testePratico.agrotis.dtos.ServicoDTO;
import com.testePratico.agrotis.dtos.convercoes.ServicoConvercoes;
import com.testePratico.agrotis.dtos.response.LaboratorioResponse;
import com.testePratico.agrotis.dtos.response.PropriedadeResponse;
import com.testePratico.agrotis.dtos.response.ServicoResponse;
import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.model.Propriedade;
import com.testePratico.agrotis.model.Servico;
import com.testePratico.agrotis.repository.ServicoRepository;
import com.testePratico.agrotis.service.ServicoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class ServicoControllerTest {

    @InjectMocks
    private ServicoController servicoController;

    @Mock
    private ServicoService servicoService;

    @Mock
    private ServicoRepository servicoRepository;

    @Mock
    private ServicoConvercoes convercoes;

    private Servico servico1;
    private Servico servico2;
    private ServicoDTO servicoDTO;
    private ServicoResponse servicoResponse;
    private Propriedade propriedade1;
    private PropriedadeResponse propriedadeResponse;
    private LaboratorioResponse laboratorioResponse;
    private Laboratorio laboratorio1;
    private LaboratorioDTO laboratorioDTO;
    private PropriedadeDTO propriedadeDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvarTest(){
        Mockito.when(servicoService.salvar(Mockito.any())).thenReturn(servico1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponse> response = servicoController.salvar(servicoDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ServicoResponse.class, response.getBody().getClass());
    }

    @Test
    public void editarTest(){
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(servicoResponse);
        Mockito.when(servicoService.editar(servico1, servico1.getId())).thenReturn(servico1);

        ResponseEntity<ServicoResponse> response = servicoController.editar(servicoDTO.getId(), servicoDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ServicoResponse.class, response.getBody().getClass());
    }

    @Test
    public void listarTodosTest(){
        Mockito.when(servicoService.listarTodos()).thenReturn(List.of(servico1, servico2));
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(servicoResponse);

        ResponseEntity<List<ServicoResponse>> response = servicoController.listarTodos();
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(ServicoResponse.class, response.getBody().get(0).getClass());
    }

    @Test
    public void servicoPorNomeTest(){
        Mockito.when(servicoService.servicoPorNome(Mockito.anyString())).thenReturn(List.of(servico1, servico2));
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(servicoResponse);

        ResponseEntity<List<ServicoResponse>> response = servicoController.servicoPorNome(servico1.getNome());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ServicoResponse.class, response.getBody().get(0).getClass());
    }

    @Test
    public void servicoPorIDTest(){
        Mockito.when(servicoService.servicoPorID(Mockito.anyLong())).thenReturn(servico1);
        Mockito.when(convercoes.entidadeParaResponse(Mockito.any())).thenReturn(servicoResponse);

        ResponseEntity<ServicoResponse> response = servicoController.servicoPorID(servico1.getId());
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ServicoResponse.class, response.getBody().getClass());
    }

    @Test
    public void deletarTest(){
        Mockito.doNothing().when(servicoService).deletar(Mockito.anyLong());
        ResponseEntity<Void> response = servicoController.deletar(1L);
        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        verify(servicoService, times(1)).deletar(anyLong());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    private void iniciarObjetos(){
        propriedade1 = new Propriedade(1L, "propriedade1", "17.576.196/0001-60");
        propriedadeDTO = new PropriedadeDTO(1L, "propriedade1", "17.576.196/0001-60");
        propriedadeResponse = new PropriedadeResponse(1L, "propriedade1", "17.576.196/0001-60");
        laboratorio1 = new Laboratorio(1L, "laboratorio1");
        laboratorioDTO = new LaboratorioDTO(1L, "laboratorio1");
        laboratorioResponse = new LaboratorioResponse(1L, "laboratorio1");
        servicoDTO = new ServicoDTO(3L, "teste2", LocalDateTime.now(), LocalDateTime.now().plusDays(5).plusMonths(5), propriedadeDTO, "17.576.196/0001-62", laboratorioDTO, "");
        servicoResponse = new ServicoResponse(1L, "teste1", LocalDateTime.now(), LocalDateTime.now().plusDays(3).plusMonths(3), propriedadeResponse, "17.576.196/0001-60", laboratorioResponse, "");
        servico1 = new Servico(1L, "teste1", LocalDateTime.now(), LocalDateTime.now().plusDays(3).plusMonths(3), propriedade1, "17.576.196/0001-60", laboratorio1, "");
        servico2 = new Servico(2L, "teste1", LocalDateTime.now(), LocalDateTime.now().plusDays(5).plusMonths(5), propriedade1, "17.576.196/0001-61", laboratorio1, "");
    }
}