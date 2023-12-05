package com.example.restaurante.domains.cliente;

import com.example.restaurante.domains.Endereco.RequestEnderecoDTO;
import jakarta.validation.constraints.NotNull;

public record RequestClienteDTO(
        String id,

        @NotNull
        String cpf,

        @NotNull
        String nome,

        String telefone,

        String email,

        String nascimento,


        RequestEnderecoDTO endereco
) {

}
