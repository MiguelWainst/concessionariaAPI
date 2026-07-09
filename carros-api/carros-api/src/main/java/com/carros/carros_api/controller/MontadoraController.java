package com.carros.carros_api.controller;

import com.carros.carros_api.controller.dto.MontadoraDTO;
import com.carros.carros_api.entity.Montadora;
import com.carros.carros_api.mapper.MontadoraMapper;
import com.carros.carros_api.service.MontadoraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/montadoras")
@RequiredArgsConstructor
public class MontadoraController {

    private final MontadoraMapper mapper;
    private final MontadoraService montadoraService;

    @PostMapping
    public ResponseEntity<Void> criarMontadora(@RequestBody @Valid MontadoraDTO dto) {
        Montadora entity = mapper.toEntity(dto);
        montadoraService.salvar(entity);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(entity.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<MontadoraDTO> buscarMontadoraId(@PathVariable String id) {
        return montadoraService.acharPorId(UUID.fromString(id))
                .map(montadora -> {
                    MontadoraDTO dto = mapper.toDTO(montadora);
                    return ResponseEntity.ok(dto);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarMontadora(
            @RequestBody @Valid MontadoraDTO dto,
            @PathVariable String id
    ){
        return montadoraService.acharPorId(UUID.fromString(id))
                .map(montadora -> {
                    Montadora montadoraEntity = mapper.toEntity(dto);
                    montadora.setNome(montadoraEntity.getNome());
                    montadora.setDataFundacao(montadoraEntity.getDataFundacao());
                    montadora.setPaisOrigem(montadoraEntity.getPaisOrigem());
                    montadoraService.atualizar(montadora);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletarMontadora(@PathVariable String id) {
        return montadoraService.acharPorId(UUID.fromString(id))
                .map(montadora -> {
                    montadoraService.deletar(montadora);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
