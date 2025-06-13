package ar.edu.utn.gestion_inventario.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
            JwtAuthenticationFilter jwtAuthenticationFilter,
            AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        //USUARIO

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

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(List.of("http://localhost:8000"));
        configuration.setAllowedMethods(List.of("GET","POST"));
        configuration.setAllowedHeaders(List.of("Authorization","Content-Type"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration("/**",configuration);

        return source;
    }
}