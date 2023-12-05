// Isso aqui basicamente é pra ter as classes de manipulação de tabela

package com.example.restaurante.domains.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, String> {
    List<Produto> findAllByAtivoTrue();
    List<Produto> findAllByCategoriaId(String categoria_id);
}