package com.carros.carros_api.service;

import com.carros.carros_api.entity.Montadora;
import com.carros.carros_api.repository.MontadoraRepository;
import com.carros.carros_api.validator.MontadoraValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MontadoraService {

    private final MontadoraRepository montadoraRepository;
    private final MontadoraValidator montadoraValidator;

    public void salvar(Montadora montadora) {
        montadoraValidator.validar(montadora);
        montadoraRepository.save(montadora);
    }

    public Optional<Montadora> acharPorId(UUID id) {
        return montadoraRepository.findById(id);
    }

    public void atualizar(Montadora montadora) {
        if (montadora.getId() == null) {
            throw new RuntimeException("Não é possível atualizar uma montadora sem ID. Provavelmente inexistente.");
        }
        montadoraValidator.validar(montadora);
        montadoraRepository.save(montadora);
    }

    public void deletar(Montadora montadora) {
        montadoraRepository.delete(montadora);
    }
}
