package com.carros.carros_api.validator;

import com.carros.carros_api.entity.Montadora;
import com.carros.carros_api.exceptions.RegistroDuplicadoException;
import com.carros.carros_api.repository.MontadoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MontadoraValidator {

    private final MontadoraRepository montadoraRepository;

    public void validar(Montadora montadora) {
        if (existeMontadora(montadora)) {
            throw new RegistroDuplicadoException("Esta montadora já está cadastrado!");
        }
    }

    private boolean existeMontadora(Montadora montadora) {
        Optional<Montadora> montadoraOptional = montadoraRepository.findByNomeAndDataFundacaoAndPaisOrigem(
                montadora.getNome(),
                montadora.getDataFundacao(),
                montadora.getPaisOrigem()
        );
        if (montadora.getId() == null) {
            return montadoraOptional.isPresent();
        }
        return montadoraOptional.isPresent() && !montadora.getId().equals(montadoraOptional.get().getId());
    }
}
