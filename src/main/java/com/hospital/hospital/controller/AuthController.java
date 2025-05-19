package com.hospital.hospital.controller;

import com.hospital.hospital.DTO.AuthRequest;
import com.hospital.hospital.ENUM.Role;
import com.hospital.hospital.model.User;
import com.hospital.hospital.repository.UserRepository;
import com.hospital.hospital.security.JwUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.hospital.hospital.service.UserDetailsServiceImpl;

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
    private JwUtil jwUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        System.out.println("Attempting to register user: " + user.getUsername());
        try {
            if (userRepository.findByUsername(user.getUsername()).isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "El nombre de usuario ya está en uso");
                return ResponseEntity.badRequest().body(response);
            }

            user.setRole(Role.PATIENT); 
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            User savedUser = userRepository.save(user);
            System.out.println("User registered successfully with ID: " + savedUser.getId());

       
            String token = jwUtil.generateToken(user.getUsername());
            
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Usuario registrado exitosamente");
            response.put("token", token);
            response.put("userId", savedUser.getId());
            response.put("username", savedUser.getUsername());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error al registrar usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        System.out.println("Attempting login for user: " + request.getUsername());
        try {
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUsername(), 
                    request.getPassword()
                )
            );
            
            String token = jwUtil.generateToken(request.getUsername());
            System.out.println("Login successful, token generated");
            
           
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("username", request.getUsername());
            response.put("message", "Login exitoso");
            
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            System.out.println("Login failed: " + e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("error", "Usuario o contraseña incorrectos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } catch (Exception e) {
            System.out.println("Login error: " + e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("error", "Error en la autenticación: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}