package com.carros.carros_api.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "montadoras", schema = "public")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Montadora {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private LocalDate dataFundacao;

    private String paisOrigem;

    @CreatedDate
    public LocalDate dataCadastro;

    @LastModifiedDate
    @Column(name = "data_ultima_atualizacao")
    public LocalDate dataAtualizacao;

    @Column(name = "usuario_ultima_atualizacao")
    public String usuarioAtualizacao;

    @OneToMany(mappedBy = "montadora")
    public List<Veiculo> veiculos;
}
