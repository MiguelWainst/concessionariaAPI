package com.carros.carros_api.controller.dto;

import java.util.List;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {
}
