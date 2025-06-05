package ar.edu.utn.gestion_inventario.security.configuration;

import ar.edu.utn.gestion_inventario.security.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
    @Autowired
    private UsuarioService usuarioService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        return httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(auth -> auth
                //USUARIO
                .requestMatchers(HttpMethod.POST, "/api/auth/admin").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/auth").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.PUT, "/api/auth").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                .requestMatchers(HttpMethod.PATCH, "/api/auth").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/api/auth").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.GET, "/api/auth/*").hasRole("ADMINISTRADOR")
                .requestMatchers(HttpMethod.DELETE, "/api/auth/*").hasRole("ADMINISTRADOR")
                //
                .anyRequest().authenticated()
        ).userDetailsService(usuarioService).httpBasic(Customizer.withDefaults()).sessionManagement(
                session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
    {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
