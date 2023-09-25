package fr.formation.repo;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fr.formation.model.Client;


@DataJpaTest
class ClientRepositoryTest {
    @Autowired
    private ClientRepository repoClient;
    
    @Test
    void testFindAll() {
        // given

        // when
        List<Client> clients = this.repoClient.findAll();

        // then
        Assertions.assertNotNull(clients);
        Assertions.assertTrue(clients.size() > 0);
        Assertions.assertEquals(2, clients.get(0).getId());
    }
}
