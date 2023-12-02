package com.example.restaurante.controllers;

import com.example.restaurante.domains.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EndereçoRepository endereçoRepository;

    @GetMapping
    public ResponseEntity getClientes(){
        var allClientes = clienteRepository.findAll();

        return ResponseEntity.ok(allClientes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerCliente(@RequestBody @Valid RequestClienteDTO data){
        Cliente cliente = new Cliente(data);
        Endereco endereco = new Endereco(data.endereco());
        cliente.setEndereco(endereco);

        clienteRepository.save(cliente);
        endereçoRepository.save(endereco);

        return ResponseEntity.ok(cliente);
    }
}
