package com.blog.tpfinal.dto;

import com.blog.tpfinal.enums.ERol;
import com.blog.tpfinal.model.Rol;
import com.blog.tpfinal.model.Usuario;
import com.blog.tpfinal.security.JwtUtils;
import com.blog.tpfinal.service.RolService;
import com.blog.tpfinal.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final RolService rolService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            // Autenticar al usuario
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()
                    )
            );

            // Cargar el usuario autenticado
            UserDetails userDetails = usuarioService.loadUserByUsername(loginRequest.getEmail());

            // Generar el token JWT
            String token = jwtUtils.generateToken(userDetails);

            // Obtener el rol del usuario
            Usuario usuario = usuarioService.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

            // Devolver la respuesta
            LoginResponse response = new LoginResponse(
                    token,
                    usuario.getEmail(),
                    usuario.getRol().getNombre().name()
            );

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioDTO> register(@Valid @RequestBody LoginRequest registerRequest) {
        try {
            // Verificar que el usuario no exista
            if (usuarioService.findByEmail(registerRequest.getEmail()).isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            // Obtener el rol USER por defecto
            Rol rolUser = rolService.findByNombre(ERol.USER)
                    .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));

            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setEmail(registerRequest.getEmail());
            nuevoUsuario.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
            nuevoUsuario.setRol(rolUser);

            Usuario usuarioGuardado = usuarioService.save(nuevoUsuario);

            UsuarioDTO usuarioDTO = new UsuarioDTO(
                    usuarioGuardado.getId(),
                    usuarioGuardado.getEmail(),
                    usuarioGuardado.getRol().getNombre().name()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
