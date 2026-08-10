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
    private final br.com.novaconquista.gestaolicitanc.service.ItemLicitacaoService itemService;

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

    // Rota para o Ramon enviar o PDF (Ação do seu painel)
    @PatchMapping(value = "/{id}/upload-pdf", consumes = org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LicitacaoResponseDTO> enviarPdf(
            @PathVariable Long id,
            @RequestParam("arquivo") org.springframework.web.multipart.MultipartFile arquivo) {

        LicitacaoResponseDTO licitacaoAtualizada = service.anexarPdf(id, arquivo);
        return ResponseEntity.ok(licitacaoAtualizada);
    }

    // Rota para cadastrar um novo item em um edital específico
    @PostMapping("/{id}/itens")
    public ResponseEntity<br.com.novaconquista.gestaolicitanc.dto.ItemLicitacaoResponseDTO> adicionarItem(
            @PathVariable Long id,
            @RequestBody br.com.novaconquista.gestaolicitanc.dto.ItemLicitacaoRequestDTO dto) {

        var itemCriado = itemService.adicionarItem(id, dto);
        return ResponseEntity.status(201).body(itemCriado);
    }

    // Rota para listar todos os itens de um edital específico
    @GetMapping("/{id}/itens")
    public ResponseEntity<java.util.List<br.com.novaconquista.gestaolicitanc.dto.ItemLicitacaoResponseDTO>> listarItens(@PathVariable Long id) {
        var itens = itemService.listarItensDaLicitacao(id);
        return ResponseEntity.ok(itens);
    }

    // Rota para excluir um item específico em caso de erro de digitação
    @DeleteMapping("/itens/{idItem}")
    public ResponseEntity<Void> excluirItem(@PathVariable Long idItem) {
        itemService.excluirItem(idItem);
        return ResponseEntity.noContent().build();
    }
}