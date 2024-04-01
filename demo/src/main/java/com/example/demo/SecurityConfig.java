package com.example.demo;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.*;
import org.springframework.security.config.annotation.authentication.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.provisioning.*;
import org.springframework.security.web.*;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig  {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf
                        .ignoringRequestMatchers("/api/**","/home")
                )
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/signup","/api/login","/home").permitAll()
                        .anyRequest().authenticated()

                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    /*@Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.withUsername("customer1")
                .password(passwordEncoder().encode("customer1Pass"))
                .roles("CUSTOMER")
                .build();
        UserDetails user2 = User.withUsername("employee")
                .password(passwordEncoder().encode("employeePass"))
                .roles("EMPLOYEE")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
*/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}