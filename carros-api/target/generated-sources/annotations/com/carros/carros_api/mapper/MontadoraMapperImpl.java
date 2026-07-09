package com.carros.carros_api.mapper;

import com.carros.carros_api.controller.dto.MontadoraDTO;
import com.carros.carros_api.entity.Montadora;
import java.time.LocalDate;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-09T15:20:18-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class MontadoraMapperImpl implements MontadoraMapper {

    @Override
    public Montadora toEntity(MontadoraDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Montadora montadora = new Montadora();

        montadora.setId( dto.id() );
        montadora.setNome( dto.nome() );
        montadora.setDataFundacao( dto.dataFundacao() );
        montadora.setPaisOrigem( dto.paisOrigem() );

        return montadora;
    }

    @Override
    public MontadoraDTO toDTO(Montadora montadora) {
        if ( montadora == null ) {
            return null;
        }

        UUID id = null;
        String nome = null;
        LocalDate dataFundacao = null;
        String paisOrigem = null;

        id = montadora.getId();
        nome = montadora.getNome();
        dataFundacao = montadora.getDataFundacao();
        paisOrigem = montadora.getPaisOrigem();

        MontadoraDTO montadoraDTO = new MontadoraDTO( id, nome, dataFundacao, paisOrigem );

        return montadoraDTO;
    }
}
