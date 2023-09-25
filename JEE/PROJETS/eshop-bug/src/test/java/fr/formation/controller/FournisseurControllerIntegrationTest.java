package fr.formation.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.service.FournisseurService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class FournisseurControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FournisseurService srvFournisseur;

    @Test
    void testAdd() throws Exception {
        // given
        int size = this.srvFournisseur.findAll().size() + 1;

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/fournisseur/ajouter")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "TEST")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals(size, this.srvFournisseur.findAll().size());
    }

    @Test
    void testEdit() throws Exception {
        // given
        Assertions.assertNotEquals("TEST", this.srvFournisseur.findById(1).getNom());
        
        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/fournisseur/modifier?id=1")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "TEST")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals("TEST", this.srvFournisseur.findById(1).getNom());
    }
}
