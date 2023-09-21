package fr.formation.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtHeaderFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");

        if (token != null) {
            token = token.substring(7);

            if (JwtUtil.isValid(token)) {
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                
                Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password qui sert a rien", authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        // On passe au filtre suivant ...
        filterChain.doFilter(request, response);
    }
    
}
