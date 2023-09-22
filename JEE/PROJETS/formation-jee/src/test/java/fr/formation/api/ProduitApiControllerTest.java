package fr.formation.api;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import fr.formation.model.Produit;
import fr.formation.repo.ProduitRepository;
import fr.formation.request.ProduitRequest;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProduitApiControllerTest {
	// @Autowired Réservé au contexte de SPRING
	@InjectMocks
	private ProduitApiController ctrl;

	@Mock
	private ProduitRepository repoProduit;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(ctrl).build();
	}

	@Test
	void shouldFindAllStatusOk() throws Exception {
		// this.ctrl.findAll();
		Mockito.when(this.repoProduit.findAll()).thenReturn(
			List.of(new Produit(), new Produit())
		);

		this.mockMvc.perform(
			MockMvcRequestBuilders.get("/api/produit")
		)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
		;

		Mockito.verify(this.repoProduit).findAll();
	}

	@Test
	void shouldFindByIdStatusOk() throws Exception {
		Mockito	.when(this.repoProduit.findById(1))
				.thenReturn(Optional.of(new Produit()));

		this.mockMvc.perform(
			MockMvcRequestBuilders.get("/api/produit/1")
		)
		.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void shouldFindByIdStatusNotFound() throws Exception {
		Mockito	.when(this.repoProduit.findById(1))
				.thenReturn(Optional.empty());

		this.mockMvc.perform(
			MockMvcRequestBuilders.get("/api/produit/1")
		)
		.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	void shouldNotSaveProduitWhenNomIsEmpty() {
		Assertions.assertNotNull(ctrl);
		Assertions.assertTrue(true);

		ProduitRequest request = new ProduitRequest();

		request.setNom("");
		request.setPrix(new BigDecimal("123"));
		request.setFournisseurId(1);

		ctrl.add(request);

		Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());

	}

	@Test
	void shouldNotSaveProduitWhenNomIsNull() {
		Assertions.assertNotNull(ctrl);
		Assertions.assertTrue(true);

		ProduitRequest request = new ProduitRequest();

		request.setPrix(new BigDecimal("123"));
		request.setFournisseurId(1);

		ctrl.add(request);

		Mockito.verify(this.repoProduit, Mockito.never()).save(Mockito.any());

	}

	@Test
	void shouldSaveProduit() {
		Assertions.assertNotNull(ctrl);
		Assertions.assertTrue(true);

		ProduitRequest request = new ProduitRequest();

		request.setNom("test");
		request.setPrix(new BigDecimal("123"));
		request.setFournisseurId(1);

		ctrl.add(request);

		Mockito.verify(this.repoProduit).save(Mockito.any());
	}

}
