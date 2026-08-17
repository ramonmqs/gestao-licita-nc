package br.com.novaconquista.gestaolicitanc.service;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.repository.LicitacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicitacaoService {

    private final LicitacaoRepository repository;

    @Transactional
    public Licitacao salvar(Licitacao licitacao) {
        licitacao.setStatusInterno("AGUARDANDO_PDF");
        return repository.save(licitacao);
    }

    public List<Licitacao> listarTodos() {
        return repository.findAll();
    }

    @Transactional
    public Licitacao atualizarStatusPdf(Long id, String urlPdf) {
        Licitacao licitacao = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licitação não encontrada com o ID: " + id));

        licitacao.setUrlEditalPdf(urlPdf);
        licitacao.setStatusInterno("PDF_ENVIADO");
        return repository.save(licitacao);
    }

    @Transactional
    public Licitacao atualizarLicitacao(Long id, Licitacao licitacaoAtualizada) {
        Licitacao licitacaoExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Licitação não encontrada com o ID: " + id));

        licitacaoExistente.setNumeroPregao(licitacaoAtualizada.getNumeroPregao());
        licitacaoExistente.setModalidade(licitacaoAtualizada.getModalidade());
        licitacaoExistente.setCidade(licitacaoAtualizada.getCidade());
        licitacaoExistente.setObjeto(licitacaoAtualizada.getObjeto());
        licitacaoExistente.setDataCertame(licitacaoAtualizada.getDataCertame());

        return repository.save(licitacaoExistente);
    }

    @Transactional
    public void excluirLicitacao(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Licitação não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }
}