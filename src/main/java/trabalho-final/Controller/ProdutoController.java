package Controller;

import Model.Produto;
import Repository.ProdutoRepository;
import Resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository repository;

    ProdutoResourceAssembler assembler = new ProdutoResourceAssembler();

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResource> get(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_USER")
    @PostMapping
    public ResponseEntity<ProdutoResource> create(@RequestBody Produto produto) {
        produto = repository.save(produto);
        if (produto != null) {
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResource> update(@PathVariable Long id, @RequestBody Produto produto) {
        if (produto != null) {
            produto.setId(id);
            produto = repository.save(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoResource> delete(@PathVariable Long id) {
        Produto produto = repository.findOne(id);
        if (produto != null) {
            repository.delete(produto);
            return new ResponseEntity<>(assembler.toResource(produto), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    //busca por nome
    @Secured("ROLE_USER")
    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoResource> getNome(@PathVariable String nome) {
        List<Produto> lista = repository.findByDescricaoContaining(nome);
        if (lista != null) {
            return new ResponseEntity<>(assembler.toResource((Produto) lista), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //busca por marca
    @Secured("ROLE_USER")
    @GetMapping("/marca/{marca}")
    public ResponseEntity<ProdutoResource> getMarca(@PathVariable String marca) {
        List<Produto> lista = repository.findByMarcaContaining(marca);
        if (lista != null) {
            Produto produto;
            return new ResponseEntity<>(assembler.toResource((Produto) lista), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
