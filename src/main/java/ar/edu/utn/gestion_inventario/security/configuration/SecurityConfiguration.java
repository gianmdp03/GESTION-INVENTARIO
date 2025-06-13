package ar.edu.utn.gestion_inventario.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        //USUARIO
                        .requestMatchers("/api/auth/admin").permitAll()
                        .requestMatchers("/api/auth/registrar").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/auth/login").hasRole("ADMINISTRADOR")
                        //PROVEEDOR
                        .requestMatchers("/api/proveedor").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/proveedor/*").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/proveedor/email/*").hasRole("ADMINISTRADOR")
                        //DESCUENTO
                        .requestMatchers("/api/descuento").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/descuento/*").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/descuento/fecha").hasRole("ADMINISTRADOR")
                        .requestMatchers("/api/descuento/expirados").hasRole("ADMINISTRADOR")
                        //EXISTENCIA
                        .requestMatchers("/api/existencia").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .requestMatchers("/api/existencia/*").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .requestMatchers("/api/existencia/stock/mas").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .requestMatchers("/api/existencia/stock/menos/*").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        //PRODUCTO
                        .requestMatchers("/api/producto").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .requestMatchers("/api/producto/*").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .requestMatchers("/api/producto/email/*").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .requestMatchers("/api/producto/categoria/*").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .requestMatchers("/api/producto/codigobarras/*").hasAnyRole("ADMINISTRADOR", "EMPLEADO")
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}