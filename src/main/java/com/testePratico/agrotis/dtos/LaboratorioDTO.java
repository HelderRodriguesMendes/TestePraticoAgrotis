package com.testePratico.agrotis.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LaboratorioDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Nome")
    @Length(min = 3, max = 50, message = "Nome")
    private String nome;
}
