package br.com.novaconquista.gestaolicitanc.controller;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/licitacoes")
@CrossOrigin(origins = "*") // Permite que o seu Vercel e o Localhost conversem com o Java
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
}