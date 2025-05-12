package controller;

import DTO.AuthRequest;
import ENUM.Role;
import model.User;
import repository.UserRepository;
import Security.JwUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import service.UserDetailsServiceImpl;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.BadCredentialsException;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

  @PostMapping("/register")
public ResponseEntity<?> register(@RequestBody User user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
        return ResponseEntity.badRequest().body("El nombre de usuario ya está en uso");
    }


    user.setRole(Role.PATIENT); 
    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);

    return ResponseEntity.ok("Usuario registrado exitosamente");
}

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    try {
        Authentication auth = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(), 
                request.getPassword()
            )
        );
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    } catch (BadCredentialsException e) {
        return ResponseEntity.status(401).body("Usuario o contraseña incorrectos");
    }
}
}