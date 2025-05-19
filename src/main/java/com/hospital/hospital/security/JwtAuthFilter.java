package com.hospital.hospital.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.hospital.hospital.security.JwUtil;
import java.io.IOException;
import org.springframework.util.StringUtils;
import com.hospital.hospital.service.UserDetailsServiceImpl;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwUtil jwUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                   HttpServletResponse response,
                                   FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();
        
      
        System.out.println("JwtAuthFilter - Path: " + path);
        System.out.println("JwtAuthFilter - Method: " + request.getMethod());
        
        
        if (path.startsWith("/auth/")) {
            System.out.println("JwtAuthFilter - Permitiendo acceso a ruta de autenticación: " + path);
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String jwt = getJwtFromRequest(request);
            
           
            if (jwt != null) {
                System.out.println("JwtAuthFilter - Token recibido y procesando");
            } else {
                System.out.println("JwtAuthFilter - No se recibió token JWT");
            }

            if (StringUtils.hasText(jwt) && jwUtil.validateToken(jwt)) {
                String username = jwUtil.extractUsername(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("JwtAuthFilter - Usuario autenticado: " + username);
            }
        } catch (Exception ex) {
            System.out.println("JwtAuthFilter - Error al procesar JWT: " + ex.getMessage());
           
        }

        filterChain.doFilter(request, response);
    }
    
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}