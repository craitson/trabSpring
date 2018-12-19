package Resource;

import Controller.ClienteController;
import Controller.PedidoController;
import Model.Cliente;
import Model.Pedido;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PedidoResourceAssembler extends ResourceAssemblerSupport<Pedido, PedidoResource> {

    public PedidoResourceAssembler() {
        super(Pedido.class, PedidoResource.class);
    }

    @Override
    public PedidoResource toResource(Pedido pedido) {
        return new PedidoResource(pedido, linkTo(methodOn(PedidoController.class).get(pedido.getNumero())).withSelfRel());
    }

    @Override
    public PedidoResource instantiateResource(Pedido pedido) {
        return new PedidoResource(pedido);
    }
}
