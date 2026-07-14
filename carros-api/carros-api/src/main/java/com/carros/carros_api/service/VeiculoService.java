package com.carros.carros_api.service;

import com.carros.carros_api.entity.Veiculo;
import com.carros.carros_api.repository.VeiculoRepository;
import com.carros.carros_api.validator.VeiculoValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Data
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final VeiculoValidator veiculoValidator;

    public void salvar(Veiculo veiculo) {
        veiculoValidator.validar(veiculo);
        veiculoRepository.save(veiculo);
    }

    public Optional<Veiculo> buscarPorId(UUID id) {
        return veiculoRepository.findById(id);
    }

    public void atualizar(Veiculo veiculo) {
        if (veiculo.getId() == null) {
            throw new RuntimeException("Id nulo, impossível atualizar veículo com id nulo.");
        }
        veiculoValidator.validar(veiculo);
        veiculoRepository.save(veiculo);
    }
}
