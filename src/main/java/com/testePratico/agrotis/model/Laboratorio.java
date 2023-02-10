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
public class Laboratorio implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Column(length = 75, nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "laboratorio")
    private List<Servico> pedidos = new ArrayList<>();

    public Laboratorio(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
