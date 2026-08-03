package com.martex.mini_rastreador_pedidos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              try {
                  var security =   http
                          .csrf(csrf -> csrf.disable())
                          .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                          .authorizeHttpRequests(auth -> auth
                                  .requestMatchers(HttpMethod.POST, "/login").permitAll()
                                  .requestMatchers(HttpMethod.POST, "/register").permitAll()
                                  .anyRequest().authenticated()
                          )
                          .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                          .build();

                  return security;
              }
              catch (Exception e) {
                  throw new Exception(e);
              }

    }
}
