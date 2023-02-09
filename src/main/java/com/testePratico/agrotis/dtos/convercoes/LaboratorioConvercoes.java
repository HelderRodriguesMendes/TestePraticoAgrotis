package com.testePratico.agrotis.dtos.convercoes;

import com.testePratico.agrotis.dtos.LaboratorioDTO;
import com.testePratico.agrotis.dtos.response.LaboratorioResponse;
import com.testePratico.agrotis.model.Laboratorio;
import org.modelmapper.ModelMapper;

public class LaboratorioConvercoes {

    ModelMapper mapper = new ModelMapper();

    public Laboratorio DTOParaEntidade(LaboratorioDTO laboratorioDTO){
        return mapper.map(laboratorioDTO, Laboratorio.class);
    }

    public LaboratorioDTO entidadeParaDTO(Laboratorio laboratorio){
        return mapper.map(laboratorio, LaboratorioDTO.class);
    }

    public LaboratorioResponse entidadeParaResponse(Laboratorio laboratorio){
        return mapper.map(laboratorio, LaboratorioResponse.class);
    }

    public Laboratorio responseParaEntidade(LaboratorioResponse laboratorioResponse){
        return mapper.map(laboratorioResponse, Laboratorio.class);
    }
}
