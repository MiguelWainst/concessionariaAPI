package com.carros.carros_api.controller.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.UUID;

public record MontadoraDTO(
        UUID id,
        @NotBlank(message = "O nome da montadora é obrigatório.")
        @Size(max = 50, min = 2, message = "Tamanho é inválido")
        String nome,
        @NotNull(message = "A data de fundacao é obrigatório.")
        @Past(message = "A data de fundação deve ser uma data passada")
        LocalDate dataFundacao,
        @NotBlank(message = "O pais de origem da montadora é obrigatório.")
        String paisOrigem
) {
}
