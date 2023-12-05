package com.example.restaurante.controllers;

import com.example.restaurante.domains.Categoria.Categoria;
import com.example.restaurante.domains.Categoria.CategoriaRepository;
import com.example.restaurante.domains.Categoria.RequestCategoriaDTO;
import com.example.restaurante.domains.produto.Produto;
import com.example.restaurante.domains.produto.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity getCategorias(){
        List<Categoria> categoriaList = categoriaRepository.findAll();

        return ResponseEntity.ok(categoriaList);
    }

    @GetMapping("/{id}/produtos")
    public ResponseEntity getCategoriaProdutos(@PathVariable String id){
        List<Produto> produtoList = produtoRepository.findAllByCategoriaId(id);

        return ResponseEntity.ok(produtoList);
    }
    @PostMapping
    @Transactional
    public ResponseEntity recordCategoria(@RequestBody @Valid RequestCategoriaDTO requestCategoriaDTO){
        Categoria categoria = new Categoria(requestCategoriaDTO);
        categoriaRepository.save(categoria);

        return ResponseEntity.ok(categoria);
    }
}
