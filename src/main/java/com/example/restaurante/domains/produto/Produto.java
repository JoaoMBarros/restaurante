// Isso aqui é uma entidade, indica as colunas do bd

package com.example.restaurante.domains.produto;

import com.example.restaurante.domains.Categoria.Categoria;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    private Integer preco_em_centavos;

    private Boolean ativo;

    @ManyToOne(optional = false)
    @JoinColumn(name="categoria_id", nullable = false)
    private Categoria categoria;

    public Produto(RequestProdutoDTO requestProdutoDTO){
        this.nome = requestProdutoDTO.nome();
        this.descricao = requestProdutoDTO.descricao();
        this.preco_em_centavos = requestProdutoDTO.preco_em_centavos();
        this.ativo = true;
    }
}
