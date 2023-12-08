package com.example.restaurante.controllers;

import com.example.restaurante.domains.Endereco.Endereco;
import com.example.restaurante.domains.cliente.*;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
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
        try {
            var allClientes = clienteRepository.findAllByAtivoTrue();

            return ResponseEntity.ok(allClientes);
        } catch (Exception e){
            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getCliente(@PathVariable String id){
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/endereco")
    public ResponseEntity getClienteEndereco(@PathVariable String id){
        return clienteRepository.findById(id)
                .map(cliente -> ResponseEntity.ok(cliente.getEndereco()))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity registerCliente(@RequestBody @Valid RequestClienteDTO data) {
        try {
            Cliente cliente = new Cliente(data);
            Endereco endereco = new Endereco(data.endereco());
            cliente.setEndereco(endereco);
            clienteRepository.save(cliente);

            return ResponseEntity.ok(cliente);
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (ConstraintViolationException e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.badRequest().build();
        }
    }
}
