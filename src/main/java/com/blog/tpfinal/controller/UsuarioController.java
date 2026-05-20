package com.blog.tpfinal.controller;

import com.blog.tpfinal.dto.LoginRequest;
import com.blog.tpfinal.dto.UsuarioDTO;
import com.blog.tpfinal.model.Usuario;
import com.blog.tpfinal.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        List<UsuarioDTO> usuarios = usuarioService.findAll()
                .stream()
                .map(u -> new UsuarioDTO(u.getId(), u.getEmail(), u.getRol().getNombre().name()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        return usuarioService.findById(id)
                .map(u -> new UsuarioDTO(u.getId(), u.getEmail(), u.getRol().getNombre().name()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody LoginRequest request) {
        return usuarioService.findById(id)
                .map(u -> {
                    u.setEmail(request.getEmail());
                    u.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
                    Usuario updated = usuarioService.save(u);
                    return new UsuarioDTO(updated.getId(), updated.getEmail(), updated.getRol().getNombre().name());
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
