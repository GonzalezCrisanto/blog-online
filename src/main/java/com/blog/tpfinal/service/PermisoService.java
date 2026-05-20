package com.blog.tpfinal.service;

import com.blog.tpfinal.model.Permiso;
import com.blog.tpfinal.repository.PermisoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PermisoService {

    private final PermisoRepository permisoRepository;

    public List<Permiso> findAll() {
        return permisoRepository.findAll();
    }

    public Optional<Permiso> findById(Long id) {
        return permisoRepository.findById(id);
    }

    public Permiso save(Permiso permiso) {
        return permisoRepository.save(permiso);
    }

    public void deleteById(Long id) {
        permisoRepository.deleteById(id);
    }
}
