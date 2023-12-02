package com.example.restaurante.domains.cliente;

import jakarta.persistence.*;
import lombok.*;

@Table(name="cliente")
@Entity(name="cliente")
@EqualsAndHashCode(of="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String cpf;

    private String nome;

    private String telefone;

    private String email;

    private String nascimento;

    private Boolean ativo;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    public Cliente(RequestClienteDTO requestClienteDTO) {
        this.cpf = requestClienteDTO.cpf();
        this.nome = requestClienteDTO.nome();
        this.telefone = requestClienteDTO.telefone();
        this.email = requestClienteDTO.email();
        this.nascimento = requestClienteDTO.nascimento();
        this.ativo = true;
    }
}
