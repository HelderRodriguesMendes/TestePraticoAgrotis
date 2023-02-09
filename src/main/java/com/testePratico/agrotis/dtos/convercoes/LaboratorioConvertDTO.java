package com.testePratico.agrotis.dtos.convercoes;

import com.testePratico.agrotis.dtos.LaboratorioDTO;
import com.testePratico.agrotis.model.Laboratorio;
import org.modelmapper.ModelMapper;

public class LaboratorioConvertDTO {

    ModelMapper mapper = new ModelMapper();

    public Laboratorio converterParaEntidade(LaboratorioDTO laboratorioDTO){
        return mapper.map(laboratorioDTO, Laboratorio.class);
    }

    public LaboratorioDTO converterParaDTO(Laboratorio laboratorio){
        return mapper.map(laboratorio, LaboratorioDTO.class);
    }
}
