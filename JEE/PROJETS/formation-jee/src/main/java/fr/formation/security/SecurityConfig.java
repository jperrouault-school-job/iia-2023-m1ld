package fr.formation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsService inMemory(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager inMemoryManager = new InMemoryUserDetailsManager();
        String encoded = passwordEncoder.encode("123456$");

        inMemoryManager.createUser(
            User.withUsername("jeremy")
                .password(encoded)
                .roles("ADMIN")
                .build()
        );

        return inMemoryManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
