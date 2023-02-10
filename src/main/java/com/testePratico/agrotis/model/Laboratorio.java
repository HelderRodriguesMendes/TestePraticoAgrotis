package com.testePratico.agrotis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Laboratorio implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
