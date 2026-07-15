package com.carros.carros_api.controller;

import com.carros.carros_api.controller.dto.CadastroVeiculoDTO;
import com.carros.carros_api.controller.dto.PesquisaVeiculoDTO;
import com.carros.carros_api.controller.mapper.VeiculoMapper;
import com.carros.carros_api.entity.CategoriaVeiculo;
import com.carros.carros_api.entity.Veiculo;
import com.carros.carros_api.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veiculos")
@CrossOrigin(origins = "*")
public class VeiculoController implements GenericController{

    private final VeiculoService veiculoService;
    private final VeiculoMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroVeiculoDTO dto) {
        Veiculo entity = mapper.toEntity(dto);
        veiculoService.salvar(entity);
        URI location = gerarHeaderLocation(entity.getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<PesquisaVeiculoDTO> buscarPorId(@PathVariable String id) {
        return veiculoService.buscarPorId(UUID.fromString(id))
                .map(veiculo -> {
                    PesquisaVeiculoDTO dto = mapper.toDTO(veiculo);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> atualizar(
            @RequestBody @Valid CadastroVeiculoDTO dto,
            @PathVariable String id
    ) {
        return veiculoService.buscarPorId(UUID.fromString(id)).map(veiculoAchado -> {
            Veiculo entity = mapper.toEntity(dto);
            veiculoAchado.setChassi(entity.getChassi());
            veiculoAchado.setCategoria(entity.getCategoria());
            veiculoAchado.setModelo(entity.getModelo());
            veiculoAchado.setMontadora(entity.getMontadora());
            veiculoAchado.setPreco(entity.getPreco());
            veiculoAchado.setDataFabricacao(entity.getDataFabricacao());
            veiculoService.atualizar(veiculoAchado);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable String id) {
        return veiculoService.buscarPorId(UUID.fromString(id)).map(veiculo -> {
            veiculoService.deletar(veiculo);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<PesquisaVeiculoDTO>> listarVeiculosParam(
            @RequestParam(required = false) String chassi,
            @RequestParam(required = false) String modelo,
            @RequestParam(required = false) String nomeMontadora,
            @RequestParam(required = false) CategoriaVeiculo categoria,
            @RequestParam(required = false) Integer anoFabricacao
    ) {
        List<Veiculo> veiculos = veiculoService.pesquisaParam(chassi, modelo, nomeMontadora, categoria, anoFabricacao, anoFabricacao);
        List<PesquisaVeiculoDTO> dtos = veiculos.stream().map(mapper::toDTO).toList();
        return ResponseEntity.ok(dtos);
    }
}
