package com.example.iam.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.exception.BusinessRuleException;
import com.example.iam.dto.RegisterRequest;
import com.example.iam.dto.RegisterResponse;
import com.example.iam.model.Rol;
import com.example.iam.model.Usuario;
import com.example.iam.repository.RolRepository;
import com.example.iam.repository.UsuarioRepository;

import java.util.Locale;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    private final static String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private final static String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    public UsuarioService(UsuarioRepository usuarioRepository,
                          RolRepository rolRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public RegisterResponse register(RegisterRequest request) {
        String username = request.getUsername().trim().toLowerCase(Locale.ROOT);

        if (!username.matches(EMAIL_REGEX)) {
            throw new BusinessRuleException("Email no válido, recuerde que debe tener un formato válido: example@example.com");
        }

        if (!request.getPassword().matches(PASSWORD_REGEX)) {
            throw new BusinessRuleException("Contraseña no válida, recuerde que debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula, un número y un carácter especial (@#$%^&+=)");
        }

        if (usuarioRepository.existsByUsername(username)) {
            throw new BusinessRuleException("Usuario ya se encuentra registrado");
        }

        Usuario usuarioToSave = new Usuario();
        usuarioToSave.setNombreUsuario(username);
        usuarioToSave.setContrasena(passwordEncoder.encode(request.getPassword()));
        usuarioToSave.setNombre(request.getNombre());
        usuarioToSave.setApellido(request.getApellido());

        Rol rol = rolRepository.findByNombre("USER")
                .orElseThrow(() -> new BusinessRuleException("Rol USER no encontrado"));
        Usuario usuario = usuarioRepository.save(usuarioToSave);
        if (!usuarioRepository.saveDetailUsuarioRol(usuario.getIdUsuario(), rol.getIdRol())) {
            throw new BusinessRuleException("No se pudo asignar el rol inicial al usuario");
        }
        return new RegisterResponse(usuario.getIdUsuario(), usuario.getUsername());
    }
}
