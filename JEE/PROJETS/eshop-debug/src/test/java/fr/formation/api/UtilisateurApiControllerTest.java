package fr.formation.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.repo.UtilisateurRepository;
import fr.formation.request.SubscriptionRequest;

@WebMvcTest(UtilisateurApiController.class)
@ActiveProfiles("test")
class UtilisateurApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UtilisateurRepository repoUtilisateur;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Test
    @WithMockUser
    void shouldSubscribeStatusOk() throws Exception {
        // given
        SubscriptionRequest request = SubscriptionRequest.builder()
            .username("test")
            .password("123")
            .email("test@test.fr")
            .build();

        // when
        this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/user/subscribe")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(request))
        )

        // then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.passwordEncoder).encode("123");
        Mockito.verify(this.repoUtilisateur).save(Mockito.any());
    }

    private String json(SubscriptionRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
