package com.carros.carros_api.repository.specification;

import com.carros.carros_api.entity.Montadora;
import org.springframework.data.jpa.domain.Specification;

public class MontadoraSpecs {

    public static Specification<Montadora> nomeLike(String nome) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome.toUpperCase() + "%");
    }

    public static Specification<Montadora> paisOrigemLike(String paisOrigem) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("paisOrigem")), "%" + paisOrigem.toUpperCase() + "%");
    }
}
