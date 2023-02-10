package com.testePratico.agrotis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Propriedade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 75, nullable = false)
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true)
    private  String cnpj;

    @JsonIgnore
    @OneToMany(mappedBy = "infosPropriedade")
    private List<Servico> pedidos = new ArrayList<>();

    public Propriedade(Long id, String nome, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }
}
