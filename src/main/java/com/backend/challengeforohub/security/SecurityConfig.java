package com.backend.challengeforohub.security;

import com.backend.challengeforohub.security.filter.JwtAuthenticationFilter;
import com.backend.challengeforohub.security.filter.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(authz -> authz
                        .antMatchers(HttpMethod.GET, "/users/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/users/register", "/users/reesetids").permitAll()
                        .antMatchers(HttpMethod.POST, "/users/create").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                        .antMatchers(HttpMethod.GET, "/topics/**").permitAll()
                        .antMatchers(HttpMethod.POST, "/topics/register").hasRole("USER")
                        .antMatchers(HttpMethod.PUT, "/topics/update/{id}").hasRole("ADMIN")
                        .antMatchers(HttpMethod.DELETE, "/topics/delete/{id}").hasRole("ADMIN")
                        .antMatchers("/roles/**").permitAll()
                        .anyRequest().authenticated())
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilter(new JwtValidationFilter(authenticationManager()))
                .csrf(config -> config.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

}
