package com.testePratico.agrotis.dtos;

import com.testePratico.agrotis.model.Laboratorio;
import com.testePratico.agrotis.model.Propriedade;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ServicoDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private LocalDateTime dataInicial;

    @NotNull
    private LocalDateTime dataFinal;

    @NotNull
    private Propriedade infosPropriedade;

    @NotNull
    private  String cnpj;

    @NotNull
    private Laboratorio laboratorio;

    private String observacoes;
}
