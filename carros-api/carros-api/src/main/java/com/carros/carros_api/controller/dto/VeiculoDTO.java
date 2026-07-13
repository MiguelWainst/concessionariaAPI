package com.carros.carros_api.controller.dto;

import com.carros.carros_api.entity.CategoriaVeiculo;
import com.carros.carros_api.entity.Montadora;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record VeiculoDTO(
        UUID id,
        @NotBlank(message = "O chassi é obrigatório.")
        String chassi,
        @NotBlank(message = "O modelo é obrigatório.")
        String modelo,
        @NotBlank(message = "A data de fabricação é obrigatória.")
        String dataFabricacao,
        CategoriaVeiculo categoria,
        BigDecimal preco,
        @NotNull(message = "O idmontadora é obrigatória.")
        UUID idMontadora,
        MontadoraDTO montadora
) {
}
