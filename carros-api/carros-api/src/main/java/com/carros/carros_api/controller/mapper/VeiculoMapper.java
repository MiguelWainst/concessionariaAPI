package com.carros.carros_api.controller.mapper;

import com.carros.carros_api.controller.dto.VeiculoDTO;
import com.carros.carros_api.entity.Veiculo;
import com.carros.carros_api.repository.MontadoraRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = MontadoraMapper.class)
public abstract class VeiculoMapper {

    @Autowired
    MontadoraRepository montadoraRepository;

    @Mapping(target = "montadora", expression = "java(montadoraRepository.findById(veiculoDTO.idMontadora()).orElse(null))")
    public abstract Veiculo toEntity(VeiculoDTO veiculoDTO);

    public abstract VeiculoDTO toDTO(Veiculo veiculo);
}
