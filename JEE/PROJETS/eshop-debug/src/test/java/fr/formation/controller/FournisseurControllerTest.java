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

import fr.formation.model.Fournisseur;
import fr.formation.request.FournisseurRequest;
import fr.formation.service.FournisseurService;

@WebMvcTest(FournisseurController.class)
@WithMockUser
@ActiveProfiles("test")
class FournisseurControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FournisseurService srvFournisseur;

    @Test
    void shouldFindAllOk() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/fournisseur"))
        
        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseurs"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/list"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).findAll();
    }

    @Test
    void shouldFindByIdOk() throws Exception {
        // given
        Mockito.when(this.srvFournisseur.findDetailedById(1)).thenReturn(new Fournisseur());
        
        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/fournisseur/details?id=1"))

        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseur"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/details"));

        Mockito.verify(this.srvFournisseur, Mockito.never()).findById(1);
        Mockito.verify(this.srvFournisseur, Mockito.times(1)).findDetailedById(1);
    }

    @Test
    void shouldAddOk() throws Exception {
        // given
        Mockito.when(this.srvFournisseur.findById(Mockito.anyInt())).thenReturn(new Fournisseur());

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/fournisseur/ajouter"))

        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("fournisseur"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/edit"));

        Mockito.verify(this.srvFournisseur, Mockito.never()).findById(Mockito.anyInt());
    }

    @Test
    void shouldPostAddOk() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/fournisseur/ajouter")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "new nom")
            )

        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/fournisseur"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).save(Mockito.eq(null), Mockito.any(FournisseurRequest.class));
    }

    @Test
    void shouldEditOk() throws Exception {
        // given
        Mockito.when(this.srvFournisseur.findById(Mockito.anyInt())).thenReturn(new Fournisseur());

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/fournisseur/modifier?id=1"))

        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("fournisseur"))
            .andExpect(MockMvcResultMatchers.view().name("fournisseur/edit"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).findById(1);
    }

    @Test
    void shouldPostEditOk() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(
                MockMvcRequestBuilders
                    .post("/fournisseur/modifier?id=1")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .param("nom", "new nom")
            )
        
        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/fournisseur"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).save(Mockito.eq(1), Mockito.any(FournisseurRequest.class));
    }

    @Test
    void shouldDeleteStatusRedirection() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/fournisseur/supprimer?id=1"))

        // then
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/fournisseur"))
            .andExpect(MockMvcResultMatchers.model().attributeDoesNotExist("fournisseur"));

        Mockito.verify(this.srvFournisseur, Mockito.times(1)).deleteById(1);
    }
}
