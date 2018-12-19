package trabalho;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import Model.Cliente;
import Repository.ClienteRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    ClienteRepository repository;

    @Test
    public void testSaveAluno() throws Exception {
        Cliente cliente = Cliente.builder()
                .nome("Teste").cpf("67454545676")
                .email("teste@email.com").datanascimento(new Date()).build();

        cliente = repository.save(cliente);

        assertNotNull(cliente);
        assertTrue(cliente.getId() != null);
    }

    @Test
    public void testDeleteAluno() throws Exception {
        Cliente cliente = entityManager.persist(Cliente.builder()
                .nome("teste").cpf("6545457676")
                .email("teste@email.com")
                .datanascimento(new Date()).build());

        repository.delete(cliente);
        cliente = repository.findOne(cliente.getId());

        assertNull(cliente);
    }


}
