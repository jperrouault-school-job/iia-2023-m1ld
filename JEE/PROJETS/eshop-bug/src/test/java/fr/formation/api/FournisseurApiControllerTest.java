package fr.formation.api;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import fr.formation.repo.FournisseurRepository;
import fr.formation.request.FournisseurRequest;

@WebMvcTest(FournisseurApiController.class)
@WithMockUser
@ActiveProfiles("test")
@EnableMethodSecurity(prePostEnabled = true)
class FournisseurApiControllerTest {
    private final static String NOM = "Nom de test";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FournisseurRepository repoFournisseur;

    @Test
    void shouldFindAllStatusOk() throws Exception {
        //given

        // when
        this.mockMvc.perform(
            MockMvcRequestBuilders.get("/api/fournisseur")
        )

        // then
        .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(this.repoFournisseur).findAll();
    }

    @Test
    void shouldAddStatusForbidden() throws Exception {
        // given
        FournisseurRequest request = FournisseurRequest.builder().nom(NOM).build();

        // when
        this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/fournisseur")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(request))
        )

        // then
        .andExpect(MockMvcResultMatchers.status().isForbidden());

        Mockito.verify(this.repoFournisseur, Mockito.never()).save(Mockito.any());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void shouldAddStatusOk() throws Exception {
        // given
        FournisseurRequest request = FournisseurRequest.builder().nom(NOM).build();

        // when
        this.mockMvc.perform(
            MockMvcRequestBuilders
                .post("/api/fournisseur")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.json(request))
        )

        // then
        .andExpect(MockMvcResultMatchers.status().isCreated());

        Mockito.verify(this.repoFournisseur).save(Mockito.any());
    }

    private String json(FournisseurRequest request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.writeValueAsString(request);
    }
}
