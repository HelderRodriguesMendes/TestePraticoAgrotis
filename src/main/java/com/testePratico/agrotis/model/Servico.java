package com.testePratico.agrotis.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Servico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 75, nullable = false)
    private String nome;

    @Column(name = "dataInicial", nullable = false)
    private LocalDateTime dataInicial;

    @Column(name = "dataFinal", nullable = false)
    private LocalDateTime dataFinal;

    @Column(name = "propriedadeId", nullable = false)
    private Propriedade infosPropriedade;

    @Column(name = "cnpj", nullable = false)
    private  String cnpj;

    @Column(name = "laboratorioId", nullable = false)
    private Laboratorio laboratorio;

    @Column(name = "observacoes", length = 500)
    private String observacoes;
}
