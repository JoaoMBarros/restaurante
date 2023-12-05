package com.example.restaurante.controllers;

import com.example.restaurante.domains.Endereco.Endereco;
import com.example.restaurante.domains.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity getClientes(){
        var allClientes = clienteRepository.findAll();

        return ResponseEntity.ok(allClientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity getCliente(@PathVariable String id){
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()){
            Cliente cliente = optionalCliente.get();

            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/endereco")
    public ResponseEntity getClienteEndereco(@PathVariable String id){
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();

            return ResponseEntity.ok(cliente.getEndereco());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerCliente(@RequestBody @Valid RequestClienteDTO data){
        Cliente cliente = new Cliente(data);
        Endereco endereco = new Endereco(data.endereco());
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);

        return ResponseEntity.ok(cliente);
    }
}
