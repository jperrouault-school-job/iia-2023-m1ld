package fr.formation.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import fr.formation.model.Produit;
import fr.formation.request.ProduitRequest;
import fr.formation.service.FournisseurService;
import fr.formation.service.ProduitService;

@WebMvcTest(ProduitController.class)
@WithMockUser
@ActiveProfiles("test")
class ProduitControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProduitService srvProduit;

    @MockBean
    private FournisseurService srvFournisseur;

    @Test
    void shouldFindAllOk() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/produit"))

        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("produits"))
            .andExpect(MockMvcResultMatchers.view().name("produit/list"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).findAll();
    }

    @Test
    void shouldAddOk() throws Exception {
        // given
        Mockito.when(this.srvProduit.findById(Mockito.anyInt())).thenReturn(new Produit());

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/produit/ajouter"))

        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("produit"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseurs"))
            .andExpect(MockMvcResultMatchers.view().name("produit/edit"));

        Mockito.verify(this.srvProduit, Mockito.never()).findById(Mockito.anyInt());
    }

    @Test
    void shouldPostAddOk() throws Exception {
        //given

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/produit/ajouter")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "new nom")
                    .param("prix", "50")
                    .param("fournisseurId", "1")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/produit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).save(Mockito.eq(null), Mockito.any(ProduitRequest.class));
    }

    @Test
    void shouldEditOk() throws Exception {
        // given
        Mockito.when(this.srvProduit.findById(Mockito.anyInt())).thenReturn(new Produit());

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/produit/modifier?id=1"))
        
        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("produit"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseurs"))
            .andExpect(MockMvcResultMatchers.view().name("produit/edit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).findById(1);
    }

    @Test
    void shouldPostEditOk() throws Exception {
        // given
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/produit/modifier?id=1")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "new nom")
                    .param("prix", "50")
                    .param("fournisseurId", "1")
            )

        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/produit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).save(Mockito.eq(1), Mockito.any(ProduitRequest.class));
    }

    @Test
    void shouldDeleteStatusRedirection() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/produit/supprimer?id=1"))

        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/produit"))
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("produit"));

        Mockito.verify(this.srvProduit, Mockito.times(1)).deleteById(1);
    }
}
