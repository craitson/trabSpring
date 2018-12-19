package Repository;

import Model.Cliente;
import Model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Cliente findByEndereco(String rua);

    Cliente findByCidade(String cidade);

    Cliente findByEstado(String estado);
}
