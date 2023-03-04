package com.workshop.abhisheksecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests()

                .requestMatchers("/api/v1/home/admin")
                .hasRole("ADMIN_USER")

                .requestMatchers("/api/v1/home/generaluser")
                .hasRole("GENERAL_USER")

                .requestMatchers("/api/v1/home/public")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

    return httpSecurity.build();

    }
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails generalUser=User
                .withUsername("generaluser")
                .password(passwordEncoder().encode("generaluser"))
                .roles("GENERAL_USER")
                .build();


        UserDetails adminUser=User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN_USER")
                .build();
        return new InMemoryUserDetailsManager(generalUser,adminUser);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
