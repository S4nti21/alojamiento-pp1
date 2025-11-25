package com.example.alojamiento.pp1.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.alojamiento.pp1.model.Usuario;
import com.example.alojamiento.pp1.repository.UsuarioRepository;
import com.example.alojamiento.pp1.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario actualizarDatos(Long id, Usuario datosNuevos) {
        Usuario usuario = buscarPorId(id);
        if (usuario == null) {
            return null;
        }

        usuario.setNombre(datosNuevos.getNombre());
        usuario.setApellido(datosNuevos.getApellido());
        usuario.setDni(datosNuevos.getDni());
        usuario.setImagen(datosNuevos.getImagen());

        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario login(String email, String contraseña) {
        return usuarioRepository.findByEmailAndContraseña(email, contraseña)
                .orElse(null);
    }
}
