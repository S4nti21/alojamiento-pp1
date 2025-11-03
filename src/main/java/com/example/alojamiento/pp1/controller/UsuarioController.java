package com.example.alojamiento.pp1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.alojamiento.pp1.model.Usuario;
import com.example.alojamiento.pp1.repository.UsuarioRepository;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public List<Usuario> todosUsuarios() {
        return usuarioRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioNuevo) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuario.setNombre(usuarioNuevo.getNombre());
                    usuario.setApellido(usuarioNuevo.getApellido());
                    usuario.setDni(usuarioNuevo.getDni());
                    usuario.setImagen(usuarioNuevo.getImagen());
                    return usuarioRepository.save(usuario);
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario datosLogin) {
        Usuario usuario = usuarioRepository.findByEmail(datosLogin.getEmail());
        if (usuario != null && usuario.getContraseña().equals(datosLogin.getContraseña())) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Usuario correcto",
                    "usuario", usuario));
        } else {
            return ResponseEntity.status(400).body(Map.of(
                    "mensaje", "Email o contraseña incorrecta"));
        }
    }

}
