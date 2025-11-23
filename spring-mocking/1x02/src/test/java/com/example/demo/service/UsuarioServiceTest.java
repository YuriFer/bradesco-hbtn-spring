package com.example.demo.service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void setUp() {
    }

    @Test
    void deveRetornarUsuarioQuandoUsuarioExistir() {
        Usuario usuarioEsperado = new Usuario(1L, "João", "joao@email.com");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioEsperado));
        
        Usuario resultado = usuarioService.buscarUsuarioPorId(1L);
        
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("João", resultado.getNome());
        assertEquals("joao@email.com", resultado.getEmail());
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());
        
        assertThrows(RuntimeException.class, () -> usuarioService.buscarUsuarioPorId(1L));
        verify(usuarioRepository).findById(1L);
    }

    @Test
    void deveSalvarUsuarioComSucesso() {
        Usuario usuarioParaSalvar = new Usuario(null, "Maria", "maria@email.com");
        Usuario usuarioSalvo = new Usuario(2L, "Maria", "maria@email.com");
        when(usuarioRepository.save(usuarioParaSalvar)).thenReturn(usuarioSalvo);
        
        Usuario resultado = usuarioService.salvarUsuario(usuarioParaSalvar);
        
        assertNotNull(resultado);
        assertEquals(2L, resultado.getId());
        assertEquals("Maria", resultado.getNome());
        assertEquals("maria@email.com", resultado.getEmail());
        verify(usuarioRepository).save(usuarioParaSalvar);
    }
}