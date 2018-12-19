package Repository;

import Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByDescricaoContaining(String descricao);

    List<Produto> findByMarcaContaining(String descricao);

    boolean findByDescricaoExists(String descricao);
}
