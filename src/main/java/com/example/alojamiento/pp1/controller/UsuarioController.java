package com.example.alojamiento.pp1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.alojamiento.pp1.model.Usuario;
import com.example.alojamiento.pp1.service.UsuarioService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> todosUsuarios() {
        return usuarioService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        Usuario nuevo = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarPorId(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable Long id, @RequestBody Usuario datosNuevos) {
        Usuario actualizado = usuarioService.actualizarDatos(id, datosNuevos);
        return actualizado != null
                ? ResponseEntity.ok(actualizado)
                : ResponseEntity.status(404).body(Map.of("mensaje", "Usuario no encontrado"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {

        String email = loginData.get("email");
        String contraseña = loginData.get("contraseña");

        Usuario usuario = usuarioService.login(email, contraseña);

        if (usuario != null) {
            return ResponseEntity.ok(Map.of(
                    "mensaje", "Usuario correcto",
                    "usuario", usuario
            ));
        }

        return ResponseEntity.status(400).body(Map.of(
                "mensaje", "Email o contraseña incorrecta"
        ));
    }
}
