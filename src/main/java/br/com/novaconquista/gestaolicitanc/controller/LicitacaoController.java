package br.com.novaconquista.gestaolicitanc.controller;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licitacoes")
public class LicitacaoController {

    @Autowired
    private LicitacaoRepository licitacaoRepository;

    // 1. CARREGAR AS TABELAS: Retorna todos os pregões cadastrados
    @GetMapping
    public List<Licitacao> listarTodas() {
        return licitacaoRepository.findAll();
    }

    // 2. FILA DE CAPTAÇÃO: Marivaldo envia os dados básicos de um novo pregão
    @PostMapping
    public ResponseEntity<Licitacao> criarLicitacao(@RequestBody Licitacao novaLicitacao) {
        // Trava de segurança: Todo pregão novo entra aguardando o seu PDF
        novaLicitacao.setStatus("pendente_pdf");
        novaLicitacao.setTemPdf(false);

        Licitacao salva = licitacaoRepository.save(novaLicitacao);
        return ResponseEntity.ok(salva);
    }

    // 3. SUA CENTRAL DE COMANDO: Você altera o Status e a Data de Retorno
    @PutMapping("/{id}/status")
    public ResponseEntity<Licitacao> atualizarStatus(
            @PathVariable Long id,
            @RequestBody Licitacao dadosAtualizados) {

        Optional<Licitacao> licitacaoExistente = licitacaoRepository.findById(id);

        if (licitacaoExistente.isPresent()) {
            Licitacao licitacao = licitacaoExistente.get();

            // Atualiza apenas o que você modificou na sua tela
            licitacao.setStatus(dadosAtualizados.getStatus());
            licitacao.setDataRetorno(dadosAtualizados.getDataRetorno());

            Licitacao atualizada = licitacaoRepository.save(licitacao);
            return ResponseEntity.ok(atualizada);
        }

        return ResponseEntity.notFound().build();
    }

    // 4. EXCLUSÃO: Remove um edital incorreto ou cancelado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLicitacao(@PathVariable Long id) {
        if (licitacaoRepository.existsById(id)) {
            licitacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ROTA PARA RAMON FAZER O UPLOAD
    @PostMapping("/{id}/upload")
    public ResponseEntity<Void> uploadPdf(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Optional<Licitacao> licitacaoExistente = licitacaoRepository.findById(id);
        if (licitacaoExistente.isPresent()) {
            try {
                Licitacao licitacao = licitacaoExistente.get();
                licitacao.setArquivoPdf(file.getBytes());
                licitacao.setNomeArquivoPdf(file.getOriginalFilename());
                licitacao.setTemPdf(true);
                licitacaoRepository.save(licitacao);
                return ResponseEntity.ok().build();
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    // ROTA PARA MARIVALDO (E RAMON) BAIXAREM O ARQUIVO
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadPdf(@PathVariable Long id) {
        Optional<Licitacao> licitacaoExistente = licitacaoRepository.findById(id);
        if (licitacaoExistente.isPresent() && licitacaoExistente.get().getArquivoPdf() != null) {
            Licitacao licitacao = licitacaoExistente.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + licitacao.getNomeArquivoPdf() + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(licitacao.getArquivoPdf());
        }
        return ResponseEntity.notFound().build();
    }
}