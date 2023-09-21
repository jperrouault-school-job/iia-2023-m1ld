package fr.formation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
    // @Bean
    // public UserDetailsService inMemory(PasswordEncoder passwordEncoder) {
    //     InMemoryUserDetailsManager inMemoryManager = new InMemoryUserDetailsManager();
    //     String encoded = passwordEncoder.encode("123456$");

    //     inMemoryManager.createUser(
    //         User.withUsername("jeremy")
    //             .password(encoded)
    //             .roles("ADMIN")
    //             .build()
    //     );

    //     return inMemoryManager;
    // }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtHeaderFilter) throws Exception {
        http.authorizeHttpRequests(authorization -> {
            authorization.requestMatchers("/api/user/**").permitAll();
            authorization.requestMatchers("/login").permitAll();
            authorization.requestMatchers("/**").authenticated();
        });

        // http.formLogin();
        // http.httpBasic();
        http.csrf(c -> c.disable());

        // On désactive les Cookies
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        
        // http.addFilterBefore(demoFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtHeaderFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
