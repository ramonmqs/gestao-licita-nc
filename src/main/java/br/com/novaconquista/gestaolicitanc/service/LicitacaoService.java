package br.com.novaconquista.gestaolicitanc.service;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.repository.LicitacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LicitacaoService {

    @Autowired
    private LicitacaoRepository repository;

    public List<Licitacao> listarTodas() {
        return repository.findAll();
    }

    public Licitacao salvar(Licitacao licitacao) {
        return repository.save(licitacao);
    }

    public Licitacao atualizar(Long id, Licitacao licitacaoAtualizada) {
        Optional<Licitacao> existente = repository.findById(id);
        if (existente.isPresent()) {
            Licitacao l = existente.get();
            l.setOrgao(licitacaoAtualizada.getOrgao());
            l.setNumero(licitacaoAtualizada.getNumero());
            l.setDataPregao(licitacaoAtualizada.getDataPregao());
            l.setObjeto(licitacaoAtualizada.getObjeto());
            l.setStatus(licitacaoAtualizada.getStatus());
            l.setDataRetorno(licitacaoAtualizada.getDataRetorno());
            l.setTemPdf(licitacaoAtualizada.isTemPdf());
            l.setUrlPdf(licitacaoAtualizada.getUrlPdf());
            return repository.save(l);
        }
        return null;
    }
}