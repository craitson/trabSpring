package Controller;

import Model.Cliente;
import Repository.ClienteRepository;
import Repository.EnderecoRepository;
import Resource.ClienteResourceAssembler;
import Resource.ClienteResurce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository repository;

    @Autowired
    EnderecoRepository enderecoRepository;

    ClienteResourceAssembler assembler = new ClienteResourceAssembler();

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResurce> get(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Secured("ROLE_MANAGER")
    @PostMapping
    public ResponseEntity<ClienteResurce> create(@RequestBody Cliente cliente) {
        cliente = repository.save(cliente);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResurce> update(@PathVariable Long id, @RequestBody Cliente cliente) {
        if (cliente != null) {
            cliente.setId(id);
            cliente = repository.save(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Secured("ROLE_MANAGER")
    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResurce> delete(@PathVariable Long id) {
        Cliente cliente = repository.findOne(id);
        if (cliente != null) {
            repository.delete(cliente);
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    //Busca por endereco
    @Secured("ROLE_USER")
    @GetMapping("/rua/{rua}")
    public ResponseEntity<ClienteResurce> getRua(@PathVariable String rua) {
        Cliente cliente = enderecoRepository.findByEndereco(rua);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Busca por cidade
    @Secured("ROLE_USER")
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<ClienteResurce> getcidade(@PathVariable String cidade) {
        Cliente cliente = enderecoRepository.findByCidade(cidade);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Busca por Estado
    @Secured("ROLE_USER")
    @GetMapping("/estado/{estado}")
    public ResponseEntity<ClienteResurce> getestado(@PathVariable String estado) {
        Cliente cliente = enderecoRepository.findByEstado(estado);
        if (cliente != null) {
            return new ResponseEntity<>(assembler.toResource(cliente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
