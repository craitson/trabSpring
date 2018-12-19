package Resource;

import Controller.ProdutoController;
import Model.Produto;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ProdutoResourceAssembler extends ResourceAssemblerSupport<Produto, ProdutoResource> {

    public ProdutoResourceAssembler() {
        super(Produto.class, ProdutoResource.class);
    }

    @Override
    public ProdutoResource toResource(Produto produto) {
        return new ProdutoResource(produto, linkTo(methodOn(ProdutoController.class).get(produto.getId())).withSelfRel());
    }

    @Override
    public ProdutoResource instantiateResource(Produto produto) {
        return new ProdutoResource(produto);
    }
}