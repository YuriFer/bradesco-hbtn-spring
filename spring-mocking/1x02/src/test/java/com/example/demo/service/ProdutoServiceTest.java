package com.example.demo.service;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {


    @Mock
    private ProdutoRepository produtoRepository;


    @InjectMocks
    private ProdutoService produtoService;


    @Test
    void deveRetornarProdutoQuandoIdExistir() {
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.of(new Produto(1L, "Produto 1", 10.0)));
        Produto produto = produtoService.buscarPorId(1L);
        assertNotNull(produto);
        assertEquals(1L, produto.getId());
        assertEquals("Produto 1", produto.getNome());
        assertEquals(10.0, produto.getPreco());
    }


    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
        Mockito.when(produtoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> produtoService.buscarPorId(1L));
    }
}
