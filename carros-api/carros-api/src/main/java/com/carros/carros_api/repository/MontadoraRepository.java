package com.carros.carros_api.repository;

import com.carros.carros_api.entity.Montadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, UUID> {

    Optional<Montadora> findByNomeAndDataFundacaoAndPaisOrigem(String nome, LocalDate dataFundacao, String paisOrigem);
}
