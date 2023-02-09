package com.testePratico.agrotis.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class PropriedadeDTO implements Serializable {

    private Long id;

    @NotNull
    private String nome;
}
