// Isso aqui é pra validar os campos que vem no json

package com.example.restaurante.domains.produto;

import jakarta.validation.constraints.NotNull;

public record RequestProdutoDTO(
        String id,
        @NotNull
        String nome,
        String descricao,
        String categoria,
        @NotNull
        Integer preco_em_centavos
) { }
