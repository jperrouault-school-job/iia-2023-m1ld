package fr.formation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService inMemory() {
        InMemoryUserDetailsManager inMemoryManager = new InMemoryUserDetailsManager();

        inMemoryManager.createUser(
            User.withUsername("jeremy")
                .password("{noop}123456$")
                .roles("ADMIN")
                .build()
        );

        return inMemoryManager;
    }
}
