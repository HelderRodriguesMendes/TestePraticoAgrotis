package com.testePratico.agrotis.service;

import com.testePratico.agrotis.exception.NotFoundException;
import com.testePratico.agrotis.exception.RegraNegocioException;
import com.testePratico.agrotis.model.Propriedade;
import com.testePratico.agrotis.repository.PropriedadeRepository;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PropriedadeServiceTest {

    @InjectMocks
    private PropriedadeService propriedadeService;

    @Mock
    private PropriedadeRepository propriedadeRepository;

    private Propriedade propriedade1;
    private Propriedade propriedade2;
    private Optional<Propriedade> optional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        iniciarObjetos();
    }

    @Test
    public void salvarTest(){
        Mockito.when(propriedadeRepository.save(Mockito.any())).thenReturn(propriedade1);
        Propriedade response = propriedadeService.salvar(propriedade1);
        assertNotNull(response);
        assertEquals(Propriedade.class, response.getClass());
        assertNotEquals(response.getId(), 10L);
        assertEquals(response.getId(), propriedade1.getId());
        assertEquals(response.getNome(), propriedade1.getNome());
        assertEquals(response.getCnpj(), propriedade1.getCnpj());
    }

    @Test
    public void erroAoSalvarTest(){
        Mockito.when(propriedadeRepository.findByCnpj(Mockito.anyString())).thenReturn(optional);

        try {
            propriedadeService.salvar(propriedade1);
        } catch (Exception e){
            assertEquals(RegraNegocioException.class, e.getClass());
            assertEquals("A propriedade " + propriedade1.getNome() + " já está cadastrada", e.getMessage());
        }
    }

    @Test
    public void editarTest(){
        propriedade1.setNome("teste de editar");
        Mockito.when(propriedadeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.when(propriedadeRepository.save(Mockito.any())).thenReturn(propriedade1);
        Propriedade response = propriedadeService.editar(propriedade1, propriedade1.getId());
        assertNotNull(response);
        assertEquals(Propriedade.class, response.getClass());
        assertNotEquals(response.getId(), 10L);
        assertEquals(response.getId(), propriedade1.getId());
        assertEquals(response.getNome(), propriedade1.getNome());
        assertEquals(response.getCnpj(), propriedade1.getCnpj());
    }

    @Test
    public void ErroAoEditarTest(){
        propriedade1.setNome("teste de editar");

        Mockito.when(propriedadeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.when(propriedadeRepository.save(Mockito.any())).thenReturn(propriedade1);

        try {
            Propriedade response = propriedadeService.editar(propriedade1, 5000L);
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Propriedade " + propriedade1.getNome() + " não encontrada", e.getMessage());
        }
    }

    @Test
    public void propriedadePorIDTest(){
        Mockito.when(propriedadeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Propriedade response = propriedadeService.propriedadePorID(optional.get().getId());

        assertNotNull(response);
        assertEquals(Propriedade.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
        assertEquals(response.getCnpj(), optional.get().getCnpj());
    }

    @Test
    public void erroAoBuscaPropriedadePorIDTest(){
        Mockito.when(propriedadeRepository.findById(Mockito.anyLong()))
            .thenThrow(new NotFoundException("Propriedade " + propriedade1.getId() + " não encontrada"));
        try {
            propriedadeService.propriedadePorID(propriedade1.getId());
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Propriedade " + propriedade1.getId() + " não encontrada", e.getMessage());
        }
    }

    @Test
    public void propriedadePorNomeTest(){
        Mockito.when(propriedadeRepository.findByNome(Mockito.anyString())).thenReturn(optional);
        Propriedade response = propriedadeService.propriedadePorNome(optional.get().getNome());

        assertNotNull(response);
        assertEquals(Propriedade.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
        assertEquals(response.getCnpj(), optional.get().getCnpj());
    }

    @Test
    public void erroAoBuscaPropriedadePorNomeTest(){
        Mockito.when(propriedadeRepository.findByNome(Mockito.anyString()))
            .thenThrow(new NotFoundException("Propriedade " + propriedade1.getNome() + " não encontrada"));
        try {
            propriedadeService.propriedadePorNome(propriedade1.getNome());
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Propriedade " + propriedade1.getNome() + " não encontrada", e.getMessage());
        }
    }

    @Test
    public void propriedadePorCnpjTest(){
        Mockito.when(propriedadeRepository.findByCnpj(Mockito.anyString())).thenReturn(optional);
        Propriedade response = propriedadeService.propriedadePorCnpj(optional.get().getCnpj());

        assertNotNull(response);
        assertEquals(Propriedade.class, response.getClass());
        assertEquals(response.getId(), optional.get().getId());
        assertEquals(response.getNome(), optional.get().getNome());
        assertEquals(response.getCnpj(), optional.get().getCnpj());
    }

    @Test
    public void erroAoBuscaPropriedadePorCnpjTest(){
        Mockito.when(propriedadeRepository.findByCnpj(Mockito.anyString()))
            .thenThrow(new NotFoundException("Propriedade " + propriedade1.getNome() + " não encontrada"));
        try {
            propriedadeService.propriedadePorCnpj(propriedade1.getCnpj());
        }catch (Exception e){
            assertEquals(NotFoundException.class, e.getClass());
            assertEquals("Propriedade " + propriedade1.getNome() + " não encontrada", e.getMessage());
        }
    }

    @Test
    public void listarTodosTest(){
        Mockito.when(propriedadeRepository.findAll()).thenReturn(List.of(propriedade1, propriedade2));
        List<Propriedade> propriedades = propriedadeService.listarTodas();

        assertNotNull(propriedades);
        assertEquals(2, propriedades.size());
        assertEquals(Propriedade.class, propriedades.get(1).getClass());

        assertEquals( propriedades.get(0).getId(), propriedade1.getId());
        assertEquals( propriedades.get(0).getNome(), propriedade1.getNome());
        assertEquals( propriedades.get(0).getCnpj(), propriedade1.getCnpj());
        assertEquals( propriedades.get(1).getId(), propriedade2.getId());
        assertEquals( propriedades.get(1).getNome(), propriedade2.getNome());
        assertEquals( propriedades.get(1).getCnpj(), propriedade2.getCnpj());
    }

    @Test
    public void deletarTest(){
        Mockito.when(propriedadeRepository.findById(Mockito.anyLong())).thenReturn(optional);
        Mockito.doNothing().when(propriedadeRepository).deleteById(Mockito.anyLong());
        propriedadeService.deletar(optional.get().getId());
        Mockito.verify(propriedadeRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
    }

    private void iniciarObjetos(){
        propriedade1 = new Propriedade(1L, "teste1", "17.576.196/0001-60");
        propriedade2 = new Propriedade(2L, "teste2", "16.602.343/0001-66");
        optional = Optional.of(new Propriedade(2L, "teste1", "17.576.196/0001-60"));
    }
}