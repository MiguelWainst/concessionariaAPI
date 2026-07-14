package com.carros.carros_api.repository.specification;

import com.carros.carros_api.entity.Veiculo;
import org.springframework.data.jpa.domain.Specification;

public class VeiculoSpecs {

    public static Specification<Veiculo> chassiLike(String chassi) {
        return (root, query, cb) -> cb.like(cb.upper(root.get("chassi")), "%" + chassi.toUpperCase() + "%");
    }
}
