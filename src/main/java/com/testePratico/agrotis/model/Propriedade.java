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
@Entity
public class Propriedade implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(length = 75, nullable = false)
    private String nome;

    @Getter
    @Setter
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
