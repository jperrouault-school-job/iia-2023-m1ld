package fr.formation;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.formation.api.ProduitApiController;
import fr.formation.repo.ProduitRepository;
import fr.formation.request.ProduitRequest;

// @SpringBootTest
@ExtendWith(MockitoExtension.class)
class FormationJeeApplicationTests {
	// @Autowired Réservé au contexte de SPRING
	@InjectMocks
	private ProduitApiController ctrl;

	@Mock
	private ProduitRepository repoProduit;

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
