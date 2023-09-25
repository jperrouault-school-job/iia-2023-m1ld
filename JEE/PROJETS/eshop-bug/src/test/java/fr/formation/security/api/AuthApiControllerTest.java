package fr.formation.security.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.request.AuthRequest;
import fr.formation.response.AuthResponse;
import fr.formation.security.service.SecurityService;

@ExtendWith(MockitoExtension.class)
class AuthApiControllerTest {
    private MockMvc mockMvc;

    @Mock
    private SecurityService srvSecurity;

    @InjectMocks
    private AuthApiController ctrl;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .standaloneSetup(ctrl)
            .build();
    }

    @Test
    void shouldAuthOk() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
            .username("localuser")
            .password("123456$")
            .build();

        Mockito.when(this.srvSecurity.auth(Mockito.any())).thenReturn(
            AuthResponse.builder()
                .success(true)
                .token("the.token")
                .build()  
        );

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.json(request))
            )


        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.token").value("the.token"));
        
        Mockito.verify(this.srvSecurity).auth(Mockito.any());
    }

    @Test
    void shouldAuthFail() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
            .username("localuser")
            .password("123456$")
            .build();

        Mockito.when(this.srvSecurity.auth(Mockito.any())).thenReturn(
            AuthResponse.builder()
                .success(false)
                .build()  
        );

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.json(request))
            )

        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
            .andExpect(MockMvcResultMatchers.jsonPath("$.token").isEmpty());
        
        Mockito.verify(this.srvSecurity).auth(Mockito.any());
    }

    @Test
    void shouldAuthBadRequest() throws Exception {
        // given
        AuthRequest request = AuthRequest.builder()
            .username("localuser")
            .password("")
            .build();

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/api/auth")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.json(request))
            )
            
        // then
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
        
        Mockito.verify(this.srvSecurity, Mockito.never()).auth(Mockito.any());
    }

    @Test
    void shouldAuthBadRequestWhenNoRequestBody() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.post("/api/auth"))
            
        // then
            .andExpect(MockMvcResultMatchers.status().isBadRequest());
        Mockito.verify(this.srvSecurity, Mockito.never()).auth(Mockito.any());
    }

    private String json(AuthRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
