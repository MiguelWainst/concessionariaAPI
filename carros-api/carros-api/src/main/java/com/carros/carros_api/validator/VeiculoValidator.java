package com.carros.carros_api.validator;

import com.carros.carros_api.entity.Veiculo;
import com.carros.carros_api.exceptions.RegistroDuplicadoException;
import com.carros.carros_api.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class VeiculoValidator {

    private final VeiculoRepository veiculoRepository;

    public void validar(Veiculo veiculo) {
        if (existeVeiculo(veiculo)) {
            throw new RegistroDuplicadoException("Chassi Duplicado.");
        }
    }

    private boolean existeVeiculo(Veiculo veiculo) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findByChassi(veiculo.getChassi());
        if (veiculo.getId() == null) {
            return veiculoOptional.isPresent();
        }
        return veiculoOptional.isPresent() && !veiculo.getChassi().equals(veiculoOptional.get().chassi);
    }
}
