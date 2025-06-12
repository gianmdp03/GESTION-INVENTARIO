package ar.edu.utn.gestion_inventario.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration                    // Define clase de configuración
@EnableWebSecurity                // Activar seguridad web
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 1. CSRF OFF: no hay sesión
                .formLogin(form -> form.disable()) // 2. FormLogin OFF
                .httpBasic(basic -> basic.disable()) // 3. HTTP Basic OFF

                .sessionManagement(sm -> // 4. Gestión de sesiones
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // login público
                        .anyRequest().authenticated())               // resto autenticado

                .addFilterBefore(jwtAuthFilter(), // 5. Insertar filtro JWT
                        UsernamePasswordAuthenticationFilter.class);

        return http.build(); // Construir cadena de filtros
    }

    @Bean
    public JwtAuthFilter jwtAuthFilter() {
        return new JwtAuthFilter(); // Instancia de nuestro filtro personalizado
    }
}

