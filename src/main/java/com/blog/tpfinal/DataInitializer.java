package com.blog.tpfinal;

import com.blog.tpfinal.enums.EPermiso;
import com.blog.tpfinal.enums.ERol;
import com.blog.tpfinal.model.Permiso;
import com.blog.tpfinal.model.Rol;
import com.blog.tpfinal.model.Usuario;
import com.blog.tpfinal.service.PermisoService;
import com.blog.tpfinal.service.RolService;
import com.blog.tpfinal.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final PermisoService permisoService;
    private final RolService rolService;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    public void initData() {
        // Crear permisos
        Permiso permisoRead = new Permiso();
        permisoRead.setNombre(EPermiso.READ);
        permisoService.save(permisoRead);

        Permiso permisoCreate = new Permiso();
        permisoCreate.setNombre(EPermiso.CREATE);
        permisoService.save(permisoCreate);

        Permiso permisoUpdate = new Permiso();
        permisoUpdate.setNombre(EPermiso.UPDATE);
        permisoService.save(permisoUpdate);

        Permiso permisoDelete = new Permiso();
        permisoDelete.setNombre(EPermiso.DELETE);
        permisoService.save(permisoDelete);

        // Crear rol USER (READ)
        Rol rolUser = new Rol();
        rolUser.setNombre(ERol.USER);
        rolUser.setPermisos(List.of(permisoRead));
        rolService.save(rolUser);

        // Crear rol AUTHOR (READ, CREATE, UPDATE)
        Rol rolAuthor = new Rol();
        rolAuthor.setNombre(ERol.AUTHOR);
        rolAuthor.setPermisos(List.of(permisoRead, permisoCreate, permisoUpdate));
        rolService.save(rolAuthor);

        // Crear rol ADMIN (READ, CREATE, UPDATE, DELETE)
        Rol rolAdmin = new Rol();
        rolAdmin.setNombre(ERol.ADMIN);
        rolAdmin.setPermisos(List.of(permisoRead, permisoCreate, permisoUpdate, permisoDelete));
        rolService.save(rolAdmin);

        // Crear usuario USER
        Usuario usuarioUser = new Usuario();
        usuarioUser.setEmail("user@blog.com");
        usuarioUser.setPassword(passwordEncoder.encode("password123"));
        usuarioUser.setRol(rolUser);
        usuarioService.save(usuarioUser);

        // Crear usuario AUTHOR
        Usuario usuarioAutor = new Usuario();
        usuarioAutor.setEmail("author@blog.com");
        usuarioAutor.setPassword(passwordEncoder.encode("password123"));
        usuarioAutor.setRol(rolAuthor);
        usuarioService.save(usuarioAutor);

        // Crear usuario ADMIN
        Usuario usuarioAdmin = new Usuario();
        usuarioAdmin.setEmail("admin@blog.com");
        usuarioAdmin.setPassword(passwordEncoder.encode("password123"));
        usuarioAdmin.setRol(rolAdmin);
        usuarioService.save(usuarioAdmin);

        System.out.println("✅ Datos iniciales cargados correctamente");
    }
}
