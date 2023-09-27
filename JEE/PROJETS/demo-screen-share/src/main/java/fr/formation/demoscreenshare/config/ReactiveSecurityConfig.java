package fr.formation.demoscreenshare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity.CsrfSpec;
import org.springframework.security.config.web.server.ServerHttpSecurity.HttpBasicSpec;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import fr.formation.demoscreenshare.jwt.JwtHeaderFilter;
import fr.formation.demoscreenshare.service.JpaUserDetailsService;

@Configuration
public class ReactiveSecurityConfig {
    @Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtHeaderFilter jwtHeaderFilter) {
        http.authorizeExchange(exchanges -> {
            exchanges.pathMatchers("/api/auth").permitAll();
            exchanges.anyExchange().authenticated();
        });

        http.csrf(CsrfSpec::disable);
        http.httpBasic(HttpBasicSpec::disable);
        
        http.addFilterAt(jwtHeaderFilter, SecurityWebFiltersOrder.AUTHENTICATION);
        
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    ReactiveAuthenticationManager authenticationManager(PasswordEncoder passwordEncoder, JpaUserDetailsService userDetailsService) {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService);
        
        authenticationManager.setPasswordEncoder(passwordEncoder);
        
        return authenticationManager;
    }
}
