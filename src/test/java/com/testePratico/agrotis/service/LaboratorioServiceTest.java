package com.testePratico.agrotis.service;

import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.repository.LaboratorioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LaboratorioServiceTest {

    @InjectMocks
    private LaboratorioService laboratorioService;

    @Mock
    private LaboratorioRepository laboratorioRepository;

    private Laboratorio laboratorio1;

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
        assertEquals(Laboratorio.class, response.getClass());
        assertNotEquals(response.getId(), 10L);

    }

    public void iniciarObjetos(){
        laboratorio1 = new Laboratorio(1L, "teste1");
    }
}
