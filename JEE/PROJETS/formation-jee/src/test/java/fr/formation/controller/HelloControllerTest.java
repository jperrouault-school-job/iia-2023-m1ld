package fr.formation.controller;

import static org.mockito.Mockito.only;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(HelloController.class)
@AutoConfigureMockMvc // Permet d'injecter un MockMvc
class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser
    void shouldStatusOk() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/hello")
                .param("param1", "valeur1")
            )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.model().attributeExists("user"))
            .andExpect(MockMvcResultMatchers.view().name("index"))
            ;
    }
}
