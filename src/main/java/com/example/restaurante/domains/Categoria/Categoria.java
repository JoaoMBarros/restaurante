package com.example.restaurante.domains.Categoria;

import com.example.restaurante.domains.produto.Produto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name="categoria")
@Entity(name="categoria")
@EqualsAndHashCode(of="id")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nome;

    private String descricao;

    public Categoria (RequestCategoriaDTO requestCategoriaDTO){
        this.nome = requestCategoriaDTO.nome();
        this.descricao = requestCategoriaDTO.descricao();
    }
}
