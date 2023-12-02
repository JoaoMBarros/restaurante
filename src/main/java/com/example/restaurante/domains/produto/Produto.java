// Isso aqui é uma entidade, indica as colunas do bd

package com.example.restaurante.domains.produto;

import jakarta.persistence.*;
import lombok.*;

@Table(name="produto")
@Entity(name="produto")
@EqualsAndHashCode(of="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String descricao;

    private String categoria;

    private Integer preco_em_centavos;

    private Boolean ativo;

    public Produto(RequestProdutoDTO requestProdutoDTO){
        this.nome = requestProdutoDTO.nome();
        this.categoria = requestProdutoDTO.categoria();
        this.descricao = requestProdutoDTO.descricao();
        this.preco_em_centavos = requestProdutoDTO.preco_em_centavos();
        this.ativo = true;
    }
}
