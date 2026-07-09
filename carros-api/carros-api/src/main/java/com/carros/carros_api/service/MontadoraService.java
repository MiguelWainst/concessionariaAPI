package com.carros.carros_api.service;

import com.carros.carros_api.entity.Montadora;
import com.carros.carros_api.repository.MontadoraRepository;
import com.carros.carros_api.validator.MontadoraValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.carros.carros_api.repository.specification.MontadoraSpecs.*;

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

    public List<Montadora> pesquisar(String nome, String paisOrigem) {
        Specification<Montadora> spec = Specification.where((root, query, cb) -> cb.conjunction());

        if (nome != null) {
            spec = spec.and(nomeLike(nome));
        }
        if (paisOrigem != null) {
            spec = spec.and(paisOrigemLike(paisOrigem));
        }

        return montadoraRepository.findAll(spec);
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
