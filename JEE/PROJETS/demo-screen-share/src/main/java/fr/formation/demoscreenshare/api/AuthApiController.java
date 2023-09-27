package fr.formation.demoscreenshare.api;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.demoscreenshare.jwt.JwtUtil;
import fr.formation.demoscreenshare.request.AuthRequest;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthApiController {
    private final ReactiveAuthenticationManager authenticationManager;

    @PostMapping
    public String auth(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

            this.authenticationManager.authenticate(authentication).map(auth ->
                Mono.empty().contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
            );

            return JwtUtil.generate(authentication);
        }

        catch (BadCredentialsException e) {
            return "";
        }
    }
}
