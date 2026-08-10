package br.com.novaconquista.gestaolicitanc.service;

import br.com.novaconquista.gestaolicitanc.dto.LicitacaoRequestDTO;
import br.com.novaconquista.gestaolicitanc.dto.LicitacaoResponseDTO;
import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import br.com.novaconquista.gestaolicitanc.repository.LicitacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicitacaoService {

    // Nossas duas injeções de dependência estão aqui:
    private final LicitacaoRepository repository;
    private final ArmazenamentoService armazenamentoService;

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

    @Transactional
    public LicitacaoResponseDTO anexarPdf(Long idLicitacao, MultipartFile arquivo) {
        // 1. Busca a licitação no banco.
        Licitacao licitacao = repository.findById(idLicitacao)
                .orElseThrow(() -> new RuntimeException("Licitação não encontrada com o ID: " + idLicitacao));

        // 2. Manda o arquivo para a "nuvem" e recebe o link.
        String urlPdf = armazenamentoService.fazerUploadPdf(arquivo);

        // 3. Atualiza os dados no banco.
        licitacao.setUrlEditalPdf(urlPdf);
        licitacao.setStatusInterno("PDF_ENVIADO");

        // 4. Salva e devolve o DTO atualizado para a sua tela.
        Licitacao licitacaoAtualizada = repository.save(licitacao);
        return new LicitacaoResponseDTO(licitacaoAtualizada);
    }
}