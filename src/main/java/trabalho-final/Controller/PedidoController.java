package Controller;

import Model.*;
import Repository.EnderecoRepository;
import Repository.PedidoRepository;
import Repository.ProdutoRepository;
import Resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoRepository repository;
    @Autowired
    ProdutoRepository repositoryProduto;

    PedidoResourceAssembler assembler = new PedidoResourceAssembler();

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResource> get(@PathVariable Long id) {
        Pedido pedido = repository.findOne(id);
        if (pedido != null) {
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //cadastro de pedido com validacao
    @Secured("ROLE_MANAGER")
    @PostMapping
    public ResponseEntity<PedidoResource> create(@RequestBody Pedido pedido) {

        boolean cadastrado = true;

        for (Item item : pedido.getItem()) {
            if (repositoryProduto.findByDescricaoExists(item.getProduto().getDescricao())) {
                cadastrado = false;
            }
        }

        if (cadastrado == true) {
            Pedido ped = repository.save(pedido);
            return new ResponseEntity<>(assembler.toResource(pedido), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //busca pedidos pela data de criacao
    @Secured("ROLE_USER")
    @GetMapping("/dataCriacao/{data}")
    public ResponseEntity<PedidoResource> getPorData(@PathVariable Date data) {
        List<Pedido> lista = repository.findByDataCriacao(data);
        if (lista != null) {
            return new ResponseEntity<>(assembler.toResource((Pedido) lista), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
