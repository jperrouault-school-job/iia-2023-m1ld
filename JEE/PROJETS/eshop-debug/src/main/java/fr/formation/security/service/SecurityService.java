package fr.formation.security.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import fr.formation.request.AuthRequest;
import fr.formation.response.AuthResponse;
import fr.formation.security.jwt.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class SecurityService {
    private final AuthenticationManager authenticationManager;

    public AuthResponse auth(AuthRequest authRequest) {
        try {
            log.debug("Trying to authenticate ...");

            Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            log.debug("Successfuly authenticated!");

            return AuthResponse.builder()
                .success(true)
                .token(JwtUtil.generate(authentication))
                .build();
        }

        catch (BadCredentialsException ex) {
            log.error("Can't authenticate : bad credentials.");
        }

        catch (Exception ex) {
            log.error("Can't authenticate : unknown error ({}).", ex.getClass().getSimpleName());
        }

        return AuthResponse.builder()
            .success(false)
            .build();
    }
}
