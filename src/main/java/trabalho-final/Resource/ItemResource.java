package Resource;

import Model.Endereco;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ItemResource extends Resource<Endereco> {
    public ItemResource(Endereco endereco, Link... links) {
        super(endereco, links);
    }
}
