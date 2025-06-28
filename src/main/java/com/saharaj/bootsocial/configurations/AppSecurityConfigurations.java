package com.saharaj.bootsocial.configurations;

import com.saharaj.bootsocial.userprofile.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AppSecurityConfigurations {

    private final UserService userService;

    public AppSecurityConfigurations(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/signup", "/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/signup", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLoginConfigurer -> formLoginConfigurer.defaultSuccessUrl("/home.html", true))
                .userDetailsService(userService)
                .build();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // So Spring Security can use it internally
    }

}
