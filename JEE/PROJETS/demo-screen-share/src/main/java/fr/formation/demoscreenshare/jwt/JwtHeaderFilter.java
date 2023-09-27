package fr.formation.demoscreenshare.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@Component
public class JwtHeaderFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
		String token = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        
        if (token != null) {
            token = token.substring(7);
            Optional<String> optUsername = JwtUtil.getUsername(token);

            if (optUsername.isPresent()) {
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();
                
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
                
                Authentication authentication = new UsernamePasswordAuthenticationToken(optUsername.get(), null, authorities);

                return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
            }
        }

        // On passe au filtre suivant ...
        return chain.filter(exchange);
    }
}
