package com.carros.carros_api.controller;

import com.carros.carros_api.controller.dto.VeiculoDTO;
import com.carros.carros_api.controller.mapper.VeiculoMapper;
import com.carros.carros_api.entity.Veiculo;
import com.carros.carros_api.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculos")
public class VeiculoController implements GenericController{

    private final VeiculoService veiculoService;
    private final VeiculoMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid VeiculoDTO dto) {
        Veiculo entity = mapper.toEntity(dto);
        veiculoService.salvar(entity);
        URI location = gerarHeaderLocation(entity.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<VeiculoDTO> buscarPorId(@PathVariable String id) {
        return veiculoService.buscarPorId(UUID.fromString(id))
                .map(veiculo -> {
                    VeiculoDTO dto = mapper.toDTO(veiculo);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
