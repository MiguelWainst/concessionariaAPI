package com.carros.carros_api.service;

import com.carros.carros_api.entity.CategoriaVeiculo;
import com.carros.carros_api.entity.Veiculo;
import com.carros.carros_api.repository.VeiculoRepository;
import com.carros.carros_api.validator.VeiculoValidator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.carros.carros_api.repository.specification.VeiculoSpecs.*;

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

    public void deletar(Veiculo veiculo) {
        veiculoRepository.delete(veiculo);
    }

    public List<Veiculo> pesquisaParam(
            String chassi,
            String modelo,
            String nomeMontadora,
            CategoriaVeiculo categoria,
            Integer anoFabricacao,
            Integer tamanhoPagina
    ) {
        Specification<Veiculo> spec = Specification.where((root, query, cb) -> cb.conjunction());

        if (chassi != null) {
            spec = spec.and(chassiLike(chassi));
        }
        if (modelo != null) {
            spec = spec.and(modeloLike(modelo));
        }
        if (nomeMontadora != null) {
            spec = spec.and(montadoraLike(nomeMontadora));
        }
        if (categoria != null) {
            spec = spec.and(categoriaEqual(categoria));
        }
        if (anoFabricacao != null) {
            spec = spec.and(anoFabricacaoEqual(anoFabricacao));
        }

        return veiculoRepository.findAll(spec);
    }
}
