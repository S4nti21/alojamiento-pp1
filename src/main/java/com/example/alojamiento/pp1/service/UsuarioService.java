package com.example.alojamiento.pp1.service;

import java.util.List;

import com.example.alojamiento.pp1.model.Usuario;

public interface UsuarioService {

    List<Usuario> listarTodos();

    Usuario crearUsuario(Usuario usuario);

    Usuario buscarPorId(Long id);

    Usuario actualizarDatos(Long id, Usuario usuarioNuevo);

    Usuario login(String email, String contraseña);
}
