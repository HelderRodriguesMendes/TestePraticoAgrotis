package com.testePratico.agrotis.dtos.convercoes;

import com.testePratico.agrotis.dtos.PropriedadeDTO;
import com.testePratico.agrotis.model.Propriedade;
import org.modelmapper.ModelMapper;

public class PropriedadeConvertDTO {

    ModelMapper mapper = new ModelMapper();

    public Propriedade converterParaEntidade(PropriedadeDTO propriedadeDTO){
        return mapper.map(propriedadeDTO, Propriedade.class);
    }

    public PropriedadeDTO converterParaDTO(Propriedade propriedade){
        return mapper.map(propriedade, PropriedadeDTO.class);
    }
}
