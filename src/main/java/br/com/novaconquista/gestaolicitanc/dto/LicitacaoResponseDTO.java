package br.com.novaconquista.gestaolicitanc.dto;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import java.time.LocalDateTime;

public record LicitacaoResponseDTO(
        Long id,
        String numeroPregao,
        String cidade,
        LocalDateTime dataCertame,
        String modalidade,
        String objeto,
        String portalLicitacao,
        String statusInterno,
        String urlEditalPdf,
        Boolean participar
) {
    public LicitacaoResponseDTO(Licitacao licitacao) {
        this(
                licitacao.getId(),
                licitacao.getNumeroPregao(),
                licitacao.getCidade(),
                licitacao.getDataCertame(),
                licitacao.getModalidade(),
                licitacao.getObjeto(),
                licitacao.getPortalLicitacao(),
                licitacao.getStatusInterno(),
                licitacao.getUrlEditalPdf(),
                licitacao.getParticipar()
        );
    }
}