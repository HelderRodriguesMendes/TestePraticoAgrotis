package com.testePratico.agrotis.dtos.convercoes;

import com.testePratico.agrotis.dtos.ServicoDTO;
import com.testePratico.agrotis.model.Servico;
import org.modelmapper.ModelMapper;

public class ServicoConvertDTO {

    ModelMapper mapper = new ModelMapper();

    public Servico converterParaEntidade(ServicoDTO servicoDTO){
        return mapper.map(servicoDTO, Servico.class);
    }

    public ServicoDTO converterParaDTO(Servico servico){
        return mapper.map(servico, ServicoDTO.class);
    }
}
