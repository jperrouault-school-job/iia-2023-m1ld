package fr.formation.security.jwt;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {
    private static final String USERNAME = "user";

    @Test
    void shouldGenerateJwtToken() {
        // given
        Authentication authentication = this.createAuthentication();

        // when
        String token = JwtUtil.generate(authentication);
        Optional<String> optUsername = JwtUtil.getUsername(token);

        // then
        Assertions.assertNotNull(token);
        Assertions.assertNotNull(optUsername);
        Assertions.assertTrue(optUsername.isPresent());
        Assertions.assertEquals(USERNAME, optUsername.get());
    }

    @Test
    void shouldTryValidateTokenThenFailed() {
        // given
        Authentication authentication = this.createAuthentication();
        String token = JwtUtil.generate(authentication);
        
        // On transforme le token : algo "none" et suppression de la signature
        token = "eyJhbGciOiJub25lIn0." + token.split("\\.")[1] + ".";

        // when
        Optional<String> optUsername = JwtUtil.getUsername(token);

        // then
        Assertions.assertNotNull(optUsername);
        Assertions.assertFalse(optUsername.isPresent());
    }

    @Test
    void shouldTokenValidationFailed() {
        // given
        String token = "123456$";

        // when
        Optional<String> optUsername = JwtUtil.getUsername(token);

        // then
        Assertions.assertNotNull(optUsername);
        Assertions.assertFalse(optUsername.isPresent());
    }

    @Test
    void shouldNotValidateToken() {
        Assertions.assertFalse(JwtUtil.getUsername("").isPresent());
        Assertions.assertFalse(JwtUtil.getUsername("  ").isPresent());
        Assertions.assertFalse(JwtUtil.getUsername(null).isPresent());
    }

    private Authentication createAuthentication() {
        return new UsernamePasswordAuthenticationToken(
            USERNAME,
            null,
            List.of(
                new SimpleGrantedAuthority("ROLE_USER")
            )
        );
    }
}
