package com.testePratico.agrotis.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PropriedadeResponse {

    private Long id;
    private String nome;
    private String cnpj;
}