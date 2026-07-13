package com.carros.carros_api.controller.mapper;

import com.carros.carros_api.controller.dto.MontadoraDTO;
import com.carros.carros_api.entity.Montadora;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MontadoraMapper {

    Montadora toEntity(MontadoraDTO dto);
    MontadoraDTO toDTO(Montadora montadora);
}
