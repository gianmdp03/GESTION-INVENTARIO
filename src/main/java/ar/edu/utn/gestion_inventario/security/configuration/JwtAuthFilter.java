package ar.edu.utn.gestion_inventario.security.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        // 1) Leer cabecera Authorization
        String header = request.getHeader("Authorization");

        // 2) Comprobar prefijo "Bearer "
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response); // sin token, seguir chain
            return;
        }

        // 3) Extraer token (quita "Bearer ")
        String token = header.substring(7);

        try {
            // 4) Validar firma y expiración
            if (JwtUtil.validateToken(token)) {
                // 5) Leer datos: usuario y roles
                String username = JwtUtil.getUsername(token);
                List<String> roles = JwtUtil.getRoles(token);

                // 6) Convertir roles a GrantedAuthority
                List<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                        .toList();

                // 7) Crear objeto Authentication
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                username, null, authorities);

                // 8) Guardar en contexto de seguridad
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            // 9) Token inválido o expirado: responder 401
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token inválido o expirado");
            return;
        }

        // 10) Continuar con siguiente filtro
        chain.doFilter(request, response);
    }
}

