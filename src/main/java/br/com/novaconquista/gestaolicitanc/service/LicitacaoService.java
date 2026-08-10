package br.com.novaconquista.gestaolicitanc.service;

import br.com.novaconquista.gestaolicitanc.dto.LicitacaoRequestDTO;
import br.com.novaconquista.gestaolicitanc.dto.LicitacaoResponseDTO;
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
    public LicitacaoResponseDTO cadastrarLicitacao(LicitacaoRequestDTO dto) {
        Licitacao licitacao = new Licitacao();
        licitacao.setNumeroPregao(dto.numeroPregao());
        licitacao.setCidade(dto.cidade());
        licitacao.setDataCertame(dto.dataCertame());
        licitacao.setModalidade(dto.modalidade());
        licitacao.setObjeto(dto.objeto());



        Licitacao licitacaoSalva = repository.save(licitacao);
        return new LicitacaoResponseDTO(licitacaoSalva);
    }

    public List<LicitacaoResponseDTO> listarFilaCaptacao() {
        return repository.findAll()
                .stream()
                .map(LicitacaoResponseDTO::new)
                .toList();
    }
}