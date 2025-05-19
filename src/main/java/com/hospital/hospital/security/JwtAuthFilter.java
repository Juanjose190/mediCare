package Security;

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
import Security.JwUtil;
import java.io.IOException;
import org.springframework.util.StringUtils;
import service.UserDetailsServiceImpl;

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
    
    // Permite pasar sin autenticación las rutas de autenticación
    if (path.startsWith("/auth/register")) {
        filterChain.doFilter(request, response);
        return;
    }

    try {
        String jwt = getJwtFromRequest(request);

        if (!StringUtils.hasText(jwt)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token JWT no proporcionado");
            return;
        }

        if (jwUtil.validateToken(jwt)) {
            String username = jwUtil.extractUsername(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            
            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    } catch (Exception ex) {
        logger.error("Error al procesar el JWT: " + ex.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
        return;
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