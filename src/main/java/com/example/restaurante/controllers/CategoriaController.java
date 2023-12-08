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
    public ResponseEntity getCategorias() {
        try {
            List<Categoria> categoriaList = categoriaRepository.findAll();

            return ResponseEntity.ok(categoriaList);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }


    @GetMapping("/{id}/produtos")
    public ResponseEntity getCategoriaProdutos(@PathVariable String id) {
        try {
            List<Produto> produtoList = produtoRepository.findAllByCategoriaId(id);

            if (produtoList.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(produtoList);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }
    @PostMapping
    @Transactional
    public ResponseEntity recordCategoria(@RequestBody @Valid RequestCategoriaDTO requestCategoriaDTO) {
        try {
            Categoria categoria = new Categoria(requestCategoriaDTO);
            categoriaRepository.save(categoria);

            return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.internalServerError().build();
        }
    }

}
