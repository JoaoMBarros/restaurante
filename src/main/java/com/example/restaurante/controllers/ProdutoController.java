package com.example.restaurante.controllers;

import com.example.restaurante.domains.Categoria.Categoria;
import com.example.restaurante.domains.Categoria.CategoriaRepository;
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
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity getAllProdutos(){
        try {
            var allProdutos = produtoRepository.findAllByAtivoTrue();

            return ResponseEntity.ok(allProdutos);
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity getProduto(@PathVariable String id){
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity registerProduto(@RequestBody @Valid RequestProdutoDTO data){
        return categoriaRepository.findById(data.categoria_id())
                .map(categoria -> {
                    Produto produto = new Produto(data);
                    produto.setCategoria(categoria);
                    produtoRepository.save(produto);
                    return ResponseEntity.ok(produto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    @Transactional
    public ResponseEntity updateProduto(@RequestBody @Valid RequestProdutoDTO data){
        return produtoRepository.findById(data.id())
                .map(produto -> {
                    produto.setNome(data.nome());
                    produto.setDescricao(data.descricao());
                    produto.setPreco_em_centavos(data.preco_em_centavos());
                    return ResponseEntity.ok(produto);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity deleteProduto(@RequestBody @Valid RequestProdutoDTO data){
        return produtoRepository.findById(data.id())
                .map(produto -> {
                    produto.setAtivo(false);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
