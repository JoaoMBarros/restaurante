package com.example.restaurante.domains.Categoria;

import com.example.restaurante.domains.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, String> {
}
