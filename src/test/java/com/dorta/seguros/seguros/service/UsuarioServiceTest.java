package com.dorta.seguros.seguros.service;

import com.dorta.seguros.seguros.model.Usuario;
import com.dorta.seguros.seguros.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    void setUp(){
        usuario = new Usuario();
        usuario.setId(1L);
        usuario.setUsername("user1");
        usuario.setPassword("pass");

    }

    @Test
    void testFindAll(){
        Usuario u1 = new Usuario();
        u1.setId(1L);
        u1.setUsername("iban");

        Usuario u2 = new Usuario();
        u2.setId(2L);
        u2.setUsername("Carlos");

        List<Usuario> usuariosMock = Arrays.asList(u1,u2);

        when(usuarioRepository.findAll()).thenReturn(usuariosMock);

        List<Usuario> result = usuarioService.findAll();

        //Assert

        assertNotNull(result);
        assertEquals(2,result.size());
        assertEquals("iban", result.get(0).getUsername());
        assertEquals("Carlos",result.get(1).getUsername());
    }

    @Test
    void testSave(){
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario saved = usuarioService.save(usuario);


        Usuario resultado = usuarioService.save(usuario);

        assertNotNull(saved);
        assertEquals("user1", saved.getUsername());
        verify(usuarioRepository,times(2)).save(usuario);
    }

    @Test
    void testDeleteById(){
        usuarioService.deleteById(1L);
        verify(usuarioRepository,times(1)).deleteById(1L);
    }

    @Test
    void testFindById(){
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));


        Usuario result1 = usuarioService.findById(1L).get();

        assertEquals("user1",result1.getUsername());
    }

    @Test
    void testExistisById(){
        when(usuarioRepository.existsById(1L)).thenReturn(true);

        boolean exists = usuarioService.existsById(1L);

        assertTrue(exists);
    }

    @Test
    void testExistsByUsername(){
        when(usuarioRepository.existsByUsername("iban")).thenReturn(true);

        boolean exists = usuarioService.existsByUsername("iban");

        assertTrue(exists);
    }

}
