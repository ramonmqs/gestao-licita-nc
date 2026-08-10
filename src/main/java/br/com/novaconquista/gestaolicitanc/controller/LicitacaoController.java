package br.com.novaconquista.gestaolicitanc.controller;

import br.com.novaconquista.gestaolicitanc.dto.LicitacaoRequestDTO;
import br.com.novaconquista.gestaolicitanc.dto.LicitacaoResponseDTO;
import br.com.novaconquista.gestaolicitanc.service.LicitacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licitacoes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LicitacaoController {

    private final LicitacaoService service;

    @PostMapping
    public ResponseEntity<LicitacaoResponseDTO> cadastrar(@RequestBody @Valid LicitacaoRequestDTO dto) {
        LicitacaoResponseDTO licitacaoCriada = service.cadastrarLicitacao(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(licitacaoCriada);
    }

    @GetMapping
    public ResponseEntity<List<LicitacaoResponseDTO>> listar() {
        List<LicitacaoResponseDTO> lista = service.listarFilaCaptacao();
        return ResponseEntity.ok(lista);
    }
}