//package com.example.login_service.configuration;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@RequiredArgsConstructor
//@Configuration
//public class SecurityConfig {
//    @Autowired
//    private  JwtAuthConverter jwtAuthConverter;
//
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers( "/hola/**").permitAll()
//                                .anyRequest().authenticated() // Permitir acceso sin autenticación
//                          // Todas las demás rutas requieren autenticación
//                )
//
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // No usar sesiones
//
//        return http.build();
//    }
//
//
//}