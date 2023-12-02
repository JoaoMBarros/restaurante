package com.example.restaurante.domains.cliente;

import jakarta.persistence.*;
import lombok.*;

@Table(name="endereco")
@Entity(name="endereco")
@EqualsAndHashCode(of="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String cep;

    private String estado;

    private String cidade;

    private String bairro;

    private String rua;

    private Integer numero;

    private String complemento;

    @OneToOne(mappedBy = "endereco")
    private Cliente cliente;

    public Endereco(RequestEnderecoDTO requestEnderecoDTO){
        this.cep = requestEnderecoDTO.cep();
        this.estado = requestEnderecoDTO.estado();
        this.cidade = requestEnderecoDTO.cidade();
        this.bairro = requestEnderecoDTO.bairro();
        this.rua = requestEnderecoDTO.rua();
        this.numero = requestEnderecoDTO.numero();
        this.complemento = requestEnderecoDTO.complemento();
    }

}
