package com.testePratico.agrotis.dtos.response;

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
public class ServicoResponse implements Serializable {

    private Long id;
    private String nome;
    private LocalDateTime dataInicial;
    private LocalDateTime dataFinal;
    private PropriedadeResponse infosPropriedade;
    private  String cnpj;
    private LaboratorioResponse laboratorio;
    private String observacoes;
}
