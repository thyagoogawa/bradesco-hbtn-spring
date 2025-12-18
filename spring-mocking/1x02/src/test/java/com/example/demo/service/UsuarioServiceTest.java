package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;

public class UsuarioServiceTest {

	@InjectMocks
	UsuarioService usuarioService;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void deveRetornarUsuarioQuandoIdExistir() {
		when(usuarioRepository.findById(any())).thenReturn(getUsuarioSalvo());
		
		Usuario usuarioRetornado = usuarioService.buscarUsuarioPorId(1L);
		
		assertEquals("Nome do usuario", usuarioRetornado.getNome());
	}
	
	@Test
	void deveLancarExcecaoQuandoUsuarioNaoExistir() {
		
		RuntimeException ex = assertThrows(RuntimeException.class, 
				() -> usuarioService.buscarUsuarioPorId(1L));
		
		assertEquals("Usuário não encontrado", ex.getMessage());
		
	}
	
	@Test
	void deveSalvarUsuarioComSucesso() {
		
		when(usuarioRepository.save(any())).thenReturn(getNovoUsuario());
		
		Usuario usuarioSalvo = usuarioService.salvarUsuario(getNovoUsuario());
		
		assertEquals("usuario@email.com", usuarioSalvo.getEmail());
		
	}
 	
	private Optional<Usuario> getUsuarioSalvo() {
		return Optional.of(new Usuario(1L, "Nome do usuario", "usuario@email.com"));
	}
	
	private Usuario getNovoUsuario() {
		return new Usuario(null, "Nome do usuario", "usuario@email.com");
	}
	
}
