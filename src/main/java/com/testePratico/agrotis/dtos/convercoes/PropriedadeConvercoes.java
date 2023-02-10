package com.testePratico.agrotis.dtos.convercoes;

import com.testePratico.agrotis.dtos.PropriedadeDTO;
import com.testePratico.agrotis.dtos.response.PropriedadeResponse;
import com.testePratico.agrotis.model.Propriedade;
import com.testePratico.agrotis.model.Propriedade;
import org.modelmapper.ModelMapper;

public class PropriedadeConvercoes {

    ModelMapper mapper = new ModelMapper();

    public Propriedade DTOParaEntidade(PropriedadeDTO propriedadeDTO){
        return mapper.map(propriedadeDTO, Propriedade.class);
    }

    public PropriedadeDTO entidadeParaDTO(Propriedade propriedade){
        return mapper.map(propriedade, PropriedadeDTO.class);
    }

    public PropriedadeResponse entidadeParaResponse(Propriedade propriedade){
        return mapper.map(propriedade, PropriedadeResponse.class);
    }

    public Propriedade responseParaEntidade(PropriedadeResponse propriedadeResponse){
        return mapper.map(propriedadeResponse, Propriedade.class);
    }
}
