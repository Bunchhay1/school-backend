package com.school.schoolbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration; // <--- Import ថ្មី
import org.springframework.web.cors.CorsConfigurationSource; // <--- Import ថ្មី
import org.springframework.web.cors.UrlBasedCorsConfigurationSource; // <--- Import ថ្មី

import java.util.List; // <--- Import ថ្មី

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // ✅ 1. បើក CORS (ថ្មី)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 2. បិទ CSRF
                .csrf(csrf -> csrf.disable())

                // 3. កំណត់សិទ្ធិ
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/v1/schools/**").permitAll()
                        .anyRequest().authenticated()
                )

                // 4. ដាក់ Filter
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ 5. បង្កើត Bean សម្រាប់កំណត់ច្បាប់ CORS (ថ្មី)
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // អនុញ្ញាតឱ្យមកពីគ្រប់ទិស (សម្រាប់ Dev)
        configuration.setAllowedOrigins(List.of("*"));

        // អនុញ្ញាតគ្រប់ Method (GET, POST, PUT, DELETE, OPTIONS)
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // អនុញ្ញាតគ្រប់ Header (Authorization, Content-Type...)
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}