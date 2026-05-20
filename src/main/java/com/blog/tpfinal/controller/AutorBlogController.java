package com.blog.tpfinal.controller;

import com.blog.tpfinal.model.AutorBlog;
import com.blog.tpfinal.service.AutorBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
@RequiredArgsConstructor
public class AutorBlogController {

    private final AutorBlogService autorBlogService;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<List<AutorBlog>> findAll() {
        return ResponseEntity.ok(autorBlogService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<AutorBlog> findById(@PathVariable Long id) {
        return autorBlogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<AutorBlog> save(@RequestBody AutorBlog autorBlog) {
        return ResponseEntity.status(HttpStatus.CREATED).body(autorBlogService.save(autorBlog));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<AutorBlog> update(@PathVariable Long id, @RequestBody AutorBlog autorBlog) {
        return autorBlogService.findById(id)
                .map(a -> ResponseEntity.ok(autorBlogService.save(autorBlog)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        autorBlogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
