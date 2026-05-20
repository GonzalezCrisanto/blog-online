package com.blog.tpfinal.controller;

import com.blog.tpfinal.model.Permiso;
import com.blog.tpfinal.service.PermisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permisos")
@RequiredArgsConstructor
public class PermisoController {

    private final PermisoService permisoService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<Permiso>> findAll() {
        return ResponseEntity.ok(permisoService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<Permiso> findById(@PathVariable Long id) {
        return permisoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<Permiso> save(@RequestBody Permiso permiso) {
        return ResponseEntity.status(HttpStatus.CREATED).body(permisoService.save(permiso));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        permisoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
