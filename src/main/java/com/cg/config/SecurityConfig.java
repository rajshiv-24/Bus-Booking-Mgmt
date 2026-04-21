package com.cg.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cg.security.JwtAuthFilter;

@Configuration
public class SecurityConfig {

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // ── Public endpoints (no token needed) ──────────
                        .requestMatchers("/api/auth/login").permitAll()
                        .requestMatchers("/api/auth/generateToken").permitAll()
                        .requestMatchers("/api/auth/encode/**").permitAll() // ← ADD THIS
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/**",
                                "/swagger-resources/*",
                                "/v3/api-docs/**").permitAll()

                        // ── Admin only endpoints ─────────────────────────
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // ── User endpoints (both USER and ADMIN allowed) ──
                        .requestMatchers("/api/schedules/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/bookings/**").hasAnyRole("USER", "ADMIN")

                        // ── Any other request must be authenticated ───────
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}