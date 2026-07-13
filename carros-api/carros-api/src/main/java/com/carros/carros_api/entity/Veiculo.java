package com.carros.carros_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Table(name = "veiculos")
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    public UUID id;

    @NotBlank
    public String chassi;

    @NotBlank
    public String modelo;

    @NotNull
    public LocalDate dataFabricacao;

    @Enumerated
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    public CategoriaVeiculo categoria;

    public BigDecimal preco;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "montadora_id")
    @NotNull
    public Montadora montadora;

    @CreatedDate
    public LocalDate dataCadastro;

    @LastModifiedDate
    @Column(name = "data_ultima_atualizacao")
    public LocalDate dataAtualizacao;

    @Column(name = "usuario_ultima_atualizacao")
    public String usuarioAtualizacao;
}
