package br.com.novaconquista.gestaolicitanc.controller;

import br.com.novaconquista.gestaolicitanc.model.ArquivoLicitacao;
import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.repository.ArquivoLicitacaoRepository;
import br.com.novaconquista.gestaolicitanc.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licitacoes")
public class LicitacaoController {

    @Autowired
    private LicitacaoRepository licitacaoRepository;

    @Autowired
    private ArquivoLicitacaoRepository arquivoLicitacaoRepository;

    // 1. LISTAR TODOS OS PROCESSOS
    @GetMapping
    public List<Licitacao> listarLicitacoes() {
        return licitacaoRepository.findAll();
    }

    // 2. CRIAR NOVO PROCESSO (Marivaldo)
    @PostMapping
    public Licitacao criarLicitacao(@RequestBody Licitacao licitacao) {
        return licitacaoRepository.save(licitacao);
    }

    // 3. ATUALIZAR STATUS E DILIGÊNCIAS (Ramon)
    @PutMapping("/{id}/status")
    public ResponseEntity<Licitacao> atualizarStatus(@PathVariable Long id, @RequestBody Licitacao dados) {
        Optional<Licitacao> licitacaoOpt = licitacaoRepository.findById(id);
        if (licitacaoOpt.isPresent()) {
            Licitacao licitacao = licitacaoOpt.get();
            licitacao.setStatus(dados.getStatus());
            licitacao.setDataRetorno(dados.getDataRetorno());
            licitacao.setDiligencia(dados.getDiligencia());
            return ResponseEntity.ok(licitacaoRepository.save(licitacao));
        }
        return ResponseEntity.notFound().build();
    }

    // 4. EXCLUIR PROCESSO INTEIRO
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLicitacao(@PathVariable Long id) {
        if (licitacaoRepository.existsById(id)) {
            licitacaoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // ==========================================
    // GERENCIAMENTO DE MÚLTIPLOS ARQUIVOS (PDF)
    // ==========================================

    // 5. UPLOAD DE NOVO ARQUIVO NO PROCESSO
    @PostMapping("/{id}/arquivos")
    @Transactional
    public ResponseEntity<Void> uploadArquivo(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Optional<Licitacao> licitacaoOpt = licitacaoRepository.findById(id);
        if (licitacaoOpt.isPresent()) {
            try {
                Licitacao licitacao = licitacaoOpt.get();

                ArquivoLicitacao arquivo = new ArquivoLicitacao();
                arquivo.setNomeArquivo(file.getOriginalFilename());
                arquivo.setDados(file.getBytes());
                arquivo.setLicitacao(licitacao);

                licitacao.getArquivos().add(arquivo);
                licitacaoRepository.save(licitacao); // O CascadeType.ALL cuida de salvar o arquivo no banco

                return ResponseEntity.ok().build();
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.internalServerError().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    // 6. BAIXAR ARQUIVO ESPECÍFICO
    @GetMapping("/arquivos/{arquivoId}/download")
    public ResponseEntity<byte[]> downloadArquivo(@PathVariable Long arquivoId) {
        Optional<ArquivoLicitacao> arquivoOpt = arquivoLicitacaoRepository.findById(arquivoId);
        if (arquivoOpt.isPresent()) {
            ArquivoLicitacao arquivo = arquivoOpt.get();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + arquivo.getNomeArquivo() + "\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(arquivo.getDados());
        }
        return ResponseEntity.notFound().build();
    }

    // 7. EXCLUIR ARQUIVO ESPECÍFICO
    @DeleteMapping("/arquivos/{arquivoId}")
    public ResponseEntity<Void> deletarArquivo(@PathVariable Long arquivoId) {
        if (arquivoLicitacaoRepository.existsById(arquivoId)) {
            arquivoLicitacaoRepository.deleteById(arquivoId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}