package com.example.restaurante.domains.cliente;

import jakarta.validation.constraints.NotNull;

public record RequestEnderecoDTO(
        String id,

        @NotNull
        String cep,

        String estado,

        String cidade,

        String bairro,

        String rua,

        Integer numero,

        String complemento
) {
}
