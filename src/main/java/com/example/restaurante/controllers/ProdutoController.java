package com.example.restaurante.controllers;

import com.example.restaurante.domains.produto.Produto;
import com.example.restaurante.domains.produto.ProdutoRepository;
import com.example.restaurante.domains.produto.RequestProdutoDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public ResponseEntity getAllProdutos(){
        var allProdutos = repository.findAllByAtivoTrue();

        return ResponseEntity.ok(allProdutos);
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduto(@PathVariable String id){
        Optional<Produto> optionalProduto = repository.findById(id);

        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();

            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity registerProduto(@RequestBody @Valid RequestProdutoDTO data){
        Produto produto = new Produto(data);
        repository.save(produto);

        return ResponseEntity.ok(produto);
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduto(@RequestBody @Valid RequestProdutoDTO data){
        Optional<Produto> optionalProduto = repository.findById(data.id());

        if (optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setNome(data.nome());
            produto.setDescricao(data.descricao());
            produto.setCategoria(data.categoria());
            produto.setPreco_em_centavos(data.preco_em_centavos());

            return ResponseEntity.ok(produto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity deleteProduto(@RequestBody @Valid RequestProdutoDTO data){
        Optional<Produto> optionalProduto = repository.findById(data.id());

        if(optionalProduto.isPresent()) {
            Produto produto = optionalProduto.get();
            produto.setAtivo(false);

            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
