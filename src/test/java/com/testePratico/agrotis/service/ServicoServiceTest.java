package com.testePratico.agrotis.service;

import com.testePratico.agrotis.exception.NotFoundException;
import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.model.Propriedade;
import com.testePratico.agrotis.model.Servico;
import com.testePratico.agrotis.repository.ServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServicoServiceTest {
    @InjectMocks
    private ServicoService servicoService;

    @Mock
    private ServicoRepository servicoRepository;

    private Servico servico1;
    private Servico servico2;
    private Optional<Servico> optional;
    private Propriedade propriedade1;
    private Laboratorio laboratorio1;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvarTest(){
        Mockito.when(servicoRepository.save(Mockito.any())).thenReturn(servico1);
        Servico response = servicoService.salvar(servico1);
        assertNotNull(response);
        assertEquals(Servico.class, response.getClass());
        assertNotEquals(response.getId(), 10L);
        assertEquals(response.getId(), servico1.getId());
        assertEquals(response.getNome(), servico1.getNome());
        assertEquals(response.getCnpj(), servico1.getCnpj());
    }

    @Test
    public void editarTest(){
        servico1.setNome("teste de editar");
        Mockito.when(servicoRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.when(servicoRepository.save(Mockito.any())).thenReturn(servico1);
        Servico response = servicoService.editar(servico1, servico1.getId());
        assertNotNull(response);
        assertEquals(Servico.class, response.getClass());
        assertNotEquals(response.getId(), 10L);
        assertEquals(response.getId(), servico1.getId());
        assertEquals(response.getNome(), servico1.getNome());
        assertEquals(response.getCnpj(), servico1.getCnpj());
    }

    @Test
    public void ErroAoEditarTest(){
        servico1.setNome("teste de editar");

        Mockito.when(servicoRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.when(servicoRepository.save(Mockito.any())).thenReturn(servico1);

        try {
            Servico response = servicoService.editar(servico1, 5000L);
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Servico " + servico1.getNome() + " não encontrada", e.getMessage());
        }
    }

    @Test
    public void servicoPorIDTest(){
        Mockito.when(servicoRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Servico response = servicoService.servicoPorID(optional.get().getId());

        assertNotNull(response);
        assertEquals(Servico.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
        assertEquals(response.getCnpj(), optional.get().getCnpj());
    }

    @Test
    public void erroAoBuscaServicoPorIDTest(){
        Mockito.when(servicoRepository.findById(Mockito.anyLong()))
            .thenThrow(new NotFoundException("Servico " + servico1.getId() + " não encontrado"));
        try {
            servicoService.servicoPorID(servico1.getId());
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Servico " + servico1.getId() + " não encontrado", e.getMessage());
        }
    }

    @Test
    public void servicoPorNomeTest(){
        Mockito.when(servicoRepository.findByNome(Mockito.anyString())).thenReturn(optional);
        Servico response = servicoService.servicoPorNome(optional.get().getNome());

        assertNotNull(response);
        assertEquals(Servico.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
        assertEquals(response.getCnpj(), optional.get().getCnpj());
    }

    @Test
    public void erroAoBuscaServicoPorNomeTest(){
        Mockito.when(servicoRepository.findByNome(Mockito.anyString()))
            .thenThrow(new NotFoundException("Servico " + servico1.getNome() + " não encontrada"));
        try {
            servicoService.servicoPorNome(servico1.getNome());
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Servico " + servico1.getNome() + " não encontrada", e.getMessage());
        }
    }

    @Test
    public void servicoPorCnpjTest(){
        Mockito.when(servicoRepository.findByCnpj(Mockito.anyString())).thenReturn(optional);
        Servico response = servicoService.servicoPorCnpj(optional.get().getCnpj());

        assertNotNull(response);
        assertEquals(Servico.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
        assertEquals(response.getCnpj(), optional.get().getCnpj());
    }

    @Test
    public void erroAoBuscaServicoPorCnpjTest(){
        Mockito.when(servicoRepository.findByCnpj(Mockito.anyString()))
            .thenThrow(new NotFoundException("Servico " + servico1.getNome() + " não encontrada"));
        try {
            servicoService.servicoPorCnpj(servico1.getCnpj());
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Servico " + servico1.getNome() + " não encontrada", e.getMessage());
        }
    }

    @Test
    public void listarTodosTest(){
        Mockito.when(servicoRepository.findAll()).thenReturn(List.of(servico1, servico2));
        List<Servico> servicos = servicoService.listarTodos();

        assertNotNull(servicos);
        assertEquals(2, servicos.size());
        assertEquals(Servico.class, servicos.get(1).getClass());

        assertEquals( servicos.get(0).getId(), servico1.getId());
        assertEquals( servicos.get(0).getNome(), servico1.getNome());
        assertEquals( servicos.get(0).getCnpj(), servico1.getCnpj());
        assertEquals( servicos.get(1).getId(), servico2.getId());
        assertEquals( servicos.get(1).getNome(), servico2.getNome());
        assertEquals( servicos.get(1).getCnpj(), servico2.getCnpj());
    }

    @Test
    public void deletarTest(){
        Mockito.when(servicoRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.doNothing().when(servicoRepository).deleteById(Mockito.anyLong());
        servicoService.deletar(optional.get().getId());
        Mockito.verify(servicoRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    private void iniciarObjetos(){
        propriedade1 = new Propriedade(1L, "propriedade1", "17.576.196/0001-60");
        laboratorio1 = new Laboratorio(1L, "laboratorio1");
        servico1 = new Servico(1L, "teste1", LocalDateTime.now(), LocalDateTime.now().plusDays(3).plusMonths(3), propriedade1, "17.576.196/0001-60", laboratorio1, "");
        servico2 = new Servico(2L, "teste1", LocalDateTime.now(), LocalDateTime.now().plusDays(5).plusMonths(5), propriedade1, "17.576.196/0001-61", laboratorio1, "");
        optional = Optional.of(new Servico(2L, "teste1", LocalDateTime.now(), LocalDateTime.now().plusDays(3).plusMonths(3), propriedade1, "17.576.196/0001-60", laboratorio1, ""));
    }
}