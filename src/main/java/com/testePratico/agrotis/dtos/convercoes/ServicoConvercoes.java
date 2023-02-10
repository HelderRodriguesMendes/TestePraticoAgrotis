package com.testePratico.agrotis.dtos.convercoes;

import com.testePratico.agrotis.dtos.ServicoDTO;
import com.testePratico.agrotis.dtos.response.LaboratorioResponse;
import com.testePratico.agrotis.dtos.response.PropriedadeResponse;
import com.testePratico.agrotis.dtos.response.ServicoResponse;
import com.testePratico.agrotis.model.Servico;
import org.modelmapper.ModelMapper;

public class ServicoConvercoes {

    ModelMapper mapper = new ModelMapper();
    LaboratorioConvercoes laboratorioConvercoes = new LaboratorioConvercoes();
    PropriedadeConvercoes propriedadeConvercoes = new PropriedadeConvercoes();

    public Servico DTOParaEntidade(ServicoDTO servicoDTO){
        return mapper.map(servicoDTO, Servico.class);
    }

    public ServicoDTO entidadeParaDTO(Servico servico){
        return mapper.map(servico, ServicoDTO.class);
    }

    public ServicoResponse entidadeParaResponse(Servico servico){
        LaboratorioResponse laboratorioResponse = laboratorioConvercoes.entidadeParaResponse(servico.getLaboratorio());
        PropriedadeResponse propriedadeResponse = propriedadeConvercoes.entidadeParaResponse(servico.getInfosPropriedade());
        ServicoResponse servicoResponse = mapper.map(servico, ServicoResponse.class);
        servicoResponse.setLaboratorio(laboratorioResponse);
        servicoResponse.setInfosPropriedade(propriedadeResponse);
        return servicoResponse;
    }

    public Servico responseParaEntidade(ServicoResponse servicoResponse){
        return mapper.map(servicoResponse, Servico.class);
    }
}
