package fr.formation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(HomeController.class)
@WithMockUser
@ActiveProfiles("test")
class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldStatusOk() throws Exception {
        // given

        // when
        this.mockMvc
            .perform(MockMvcRequestBuilders.get("/"))

        // then
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.view().name("home"));
    }
}
