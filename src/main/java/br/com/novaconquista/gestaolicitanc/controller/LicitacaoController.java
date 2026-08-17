package br.com.novaconquista.gestaolicitanc.controller;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.service.LicitacaoService;
import br.com.novaconquista.gestaolicitanc.service.ArmazenamentoService;
import br.com.novaconquista.gestaolicitanc.service.ItemLicitacaoService;
import br.com.novaconquista.gestaolicitanc.dto.ItemLicitacaoRequestDTO;
import br.com.novaconquista.gestaolicitanc.dto.ItemLicitacaoResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/licitacoes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LicitacaoController {

    private final LicitacaoService service;
    private final ItemLicitacaoService itemService;
    private final ArmazenamentoService armazenamentoService;

    @PostMapping
    public ResponseEntity<Licitacao> criar(@RequestBody Licitacao licitacao) {
        return ResponseEntity.status(201).body(service.salvar(licitacao));
    }

    @GetMapping
    public ResponseEntity<List<Licitacao>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @PatchMapping("/{id}/upload-pdf")
    public ResponseEntity<Licitacao> fazerUploadPdf(@PathVariable Long id, @RequestParam("arquivo") MultipartFile arquivo) {
        String urlPdf = armazenamentoService.fazerUploadPdf(arquivo);
        Licitacao licitacaoAtualizada = service.atualizarStatusPdf(id, urlPdf);
        return ResponseEntity.ok(licitacaoAtualizada);
    }

    @PostMapping("/{id}/itens")
    public ResponseEntity<ItemLicitacaoResponseDTO> adicionarItem(
            @PathVariable Long id,
            @RequestBody ItemLicitacaoRequestDTO dto) {
        var itemCriado = itemService.adicionarItem(id, dto);
        return ResponseEntity.status(201).body(itemCriado);
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemLicitacaoResponseDTO>> listarItens(@PathVariable Long id) {
        var itens = itemService.listarItensDaLicitacao(id);
        return ResponseEntity.ok(itens);
    }

    @DeleteMapping("/itens/{idItem}")
    public ResponseEntity<Void> excluirItem(@PathVariable Long idItem) {
        itemService.excluirItem(idItem);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Licitacao> atualizarLicitacao(
            @PathVariable Long id,
            @RequestBody Licitacao licitacaoAtualizada) {
        Licitacao licitacao = service.atualizarLicitacao(id, licitacaoAtualizada);
        return ResponseEntity.ok(licitacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirLicitacao(@PathVariable Long id) {
        service.excluirLicitacao(id);
        return ResponseEntity.noContent().build();
    }
}