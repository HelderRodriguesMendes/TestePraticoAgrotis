package com.testePratico.agrotis.service;

import com.testePratico.agrotis.exception.RegraNegocioException;
import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.repository.LaboratorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LaboratorioServiceTest {

    @InjectMocks
    private LaboratorioService laboratorioService;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    private Laboratorio laboratorio1;
    private Laboratorio laboratorio2;
    private Optional<Laboratorio> optional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvar(){
        Mockito.when(laboratorioRepository.save(Mockito.any())).thenReturn(laboratorio1);
        Laboratorio response = laboratorioService.salvar(laboratorio1);
        assertNotNull(response);
        assertEquals(Laboratorio.class, response.getClass());
        assertNotEquals(response.getId(), 10L);
        assertEquals(response.getId(), laboratorio1.getId());
        assertEquals(response.getNome(), laboratorio1.getNome());

    }

    @Test
    public void erroAoSalvar(){
        Mockito.when(laboratorioRepository.findByNome(Mockito.anyString())).thenReturn(optional);

        try {
            laboratorioService.salvar(laboratorio1);
        } catch (Exception e){
            assertEquals(RegraNegocioException.class, e.getClass());
            assertEquals("O Laboratotio " + laboratorio1.getNome() + " já está cadastrado", e.getMessage());
        }
    }

    @Test
    public void laboratorioPorID(){
        Mockito.when(laboratorioRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Laboratorio response = laboratorioService.laboratorioPorID(optional.get().getId());

        assertNotNull(response);
        assertEquals(Laboratorio.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
    }

    @Test
    public void erroAoBuscaLaboratorioPorID(){
        Mockito.when(laboratorioRepository.findById(Mockito.anyLong())).thenThrow(new RegraNegocioException("Laboratotio " + laboratorio1.getId() + " não encontrado"));
        try {
            laboratorioService.laboratorioPorID(laboratorio1.getId());
        }catch (Exception e){
            assertEquals(RegraNegocioException.class, e.getClass());
            assertEquals("Laboratotio " + laboratorio1.getId() + " não encontrado", e.getMessage());
        }
    }

    @Test
    public void laboratorioPorNome(){
        Mockito.when(laboratorioRepository.findByNome(Mockito.anyString())).thenReturn(optional);
        Laboratorio response = laboratorioService.laboratorioPorNome(optional.get().getNome());

        assertNotNull(response);
        assertEquals(Laboratorio.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
    }

    @Test
    public void erroAoBuscaLaboratorioPorNome(){
        Mockito.when(laboratorioRepository.findByNome(Mockito.anyString())).thenThrow(new RegraNegocioException("Laboratotio " + laboratorio1.getNome() + " não encontrado"));
        try {
            laboratorioService.laboratorioPorNome(laboratorio1.getNome());
        }catch (Exception e){
            assertEquals(RegraNegocioException.class, e.getClass());
            assertEquals("Laboratotio " + laboratorio1.getNome() + " não encontrado", e.getMessage());
        }
    }

    @Test
    public void listarTodos(){
        Mockito.when(laboratorioRepository.findAll()).thenReturn(List.of(laboratorio1, laboratorio2));
        List<Laboratorio> laboratorios = laboratorioService.listarTodos();

        assertNotNull(laboratorios);
        assertEquals(2, laboratorios.size());
        assertEquals(Laboratorio.class, laboratorios.get(1).getClass());

        assertEquals( laboratorios.get(0).getId(), laboratorio1.getId());
        assertEquals( laboratorios.get(0).getNome(), laboratorio1.getNome());
        assertEquals( laboratorios.get(1).getId(), laboratorio2.getId());
        assertEquals( laboratorios.get(1).getNome(), laboratorio2.getNome());
    }

    public void iniciarObjetos(){
        laboratorio1 = new Laboratorio(1L, "teste1");
        laboratorio2 = new Laboratorio(2L, "teste2");
        optional = Optional.of(new Laboratorio(2L, "teste1"));

    }
}
