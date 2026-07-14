package com.carros.carros_api.repository.specification;

import com.carros.carros_api.entity.CategoriaVeiculo;
import com.carros.carros_api.entity.Veiculo;
import org.springframework.data.jpa.domain.Specification;

public class VeiculoSpecs {

    public static Specification<Veiculo> chassiLike(String chassi) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("chassi")), "%" + chassi.toUpperCase() + "%");
    }

    public static Specification<Veiculo> modeloLike(String modelo) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("modelo")), "%" + modelo.toUpperCase() + "%");
    }

    public static Specification<Veiculo> montadoraLike(String montadora) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("montadora").get("nome")), "%" + montadora.toUpperCase() + "%");
    }

    public static Specification<Veiculo> categoriaEqual(CategoriaVeiculo categoria) {
        return (root, query, cb) -> cb.equal(root.get("categoria"), categoria);
    }

    public static Specification<Veiculo> anoFabricacaoEqual(Integer anoFabricacao) {
        return (root, query, cb) ->
                cb.equal(cb.function("to_char", String.class, root.get("dataFabricacao"),
                        cb.literal("YYYY")), anoFabricacao.toString());
    }
}
