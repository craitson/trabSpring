package Resource;

import Controller.ClienteController;
import Model.Cliente;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ClienteResourceAssembler extends ResourceAssemblerSupport<Cliente, ClienteResurce> {

    public ClienteResourceAssembler() {
        super(Cliente.class, ClienteResurce.class);
    }

    @Override
    public ClienteResurce toResource(Cliente cliente) {
        return new ClienteResurce(cliente, linkTo(methodOn(ClienteController.class).get(cliente.getId())).withSelfRel());
    }

    @Override
    public ClienteResurce instantiateResource(Cliente cliente) {
        return new ClienteResurce(cliente);
    }
}
