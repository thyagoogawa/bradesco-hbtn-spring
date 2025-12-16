package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTest {
	
	@Mock
    private ProdutoRepository produtoRepository;


    @InjectMocks
    private ProdutoService produtoService;


    @Test
    void deveRetornarProdutoQuandoIdExistir() {
    		
    		when(produtoRepository.findById(any())).thenReturn(Optional.of(getProduto()));
    	
    		Produto produtoRetornado = produtoService.buscarPorId(1L);
    		
    		assertNotNull(produtoRetornado);
    	
    }


    @Test
    void deveLancarExcecaoQuandoProdutoNaoExistir() {
    
    		RuntimeException ex = assertThrows(RuntimeException.class, () -> produtoService.buscarPorId(1L));
    		
    		assertEquals("Produto não encontrado", ex.getMessage());
		
    }
    
    private Produto getProduto() {
    		return new Produto(1L, "Arroz", 6.99);
    }

}
