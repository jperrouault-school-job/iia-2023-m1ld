package fr.formation.security.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import fr.formation.request.AuthRequest;
import fr.formation.response.AuthResponse;

@ExtendWith(MockitoExtension.class)
class SecurityServiceTest {
    private static final String USERNAME = "localuser";
    private static final String PASSWORD = "123456$";

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private SecurityService srvSecurity;

    @Test
    void shouldReturnSuccessTrueWithToken() {
        // given
        Authentication authentication = new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD);
        Mockito.when(this.authenticationManager.authenticate(authentication)).thenReturn(authentication);

        AuthRequest request = AuthRequest.builder()
            .username(USERNAME)
            .password(PASSWORD)
            .build();
        
        // when
        AuthResponse response = this.srvSecurity.auth(request);

        // then
        Mockito.verify(this.authenticationManager).authenticate(Mockito.any());
        Assertions.assertNotNull(response);
        Assertions.assertTrue(response.isSuccess());
        Assertions.assertNotNull(response.getToken());
    }

    @Test
    void shouldReturnSuccessFalseWithoutToken() {
        // given
        Authentication authentication = new UsernamePasswordAuthenticationToken(USERNAME, PASSWORD);
        Mockito.when(this.authenticationManager.authenticate(authentication)).thenReturn(null);

        AuthRequest request = AuthRequest.builder()
            .username(USERNAME)
            .password(PASSWORD)
            .build();
        
        // when
        AuthResponse response = this.srvSecurity.auth(request);

        // then
        Mockito.verify(this.authenticationManager).authenticate(Mockito.any());
        Assertions.assertNotNull(response);
        Assertions.assertFalse(response.isSuccess());
        Assertions.assertNull(response.getToken());
    }
}
