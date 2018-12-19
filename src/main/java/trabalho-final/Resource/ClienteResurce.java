package Resource;

import Model.Cliente;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ClienteResurce extends Resource<Cliente> {

    public ClienteResurce(Cliente cliente, Link... links) {
        super(cliente, links);
    }

}
