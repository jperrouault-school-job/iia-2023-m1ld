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

import fr.formation.service.ProduitService;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser
class ProduitControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProduitService srvProduit;

    @Test
    void testAdd() throws Exception {
        // given
        int size = this.srvProduit.findAll().size() + 1;

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/produit/ajouter")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "TEST")
                    .param("prix", "azerty")
                    .param("fournisseurId", "3")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/produit/ajouter")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "TEST")
                    .param("prix", "50")
                    .param("fournisseurId", "3")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals(size, this.srvProduit.findAll().size());
    }

    @Test
    void testEdit() throws Exception {
        // given
        Assertions.assertNotEquals("TEST", this.srvProduit.findById(2).getNom());
        
        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/produit/modifier?id=2")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "TEST")
                    .param("prix", "50")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection());

        Assertions.assertEquals("TEST", this.srvProduit.findById(2).getNom());
    }
}
