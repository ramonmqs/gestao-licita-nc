package br.com.novaconquista.gestaolicitanc.dto;

import br.com.novaconquista.gestaolicitanc.model.Licitacao;
import java.time.LocalDateTime;

public class LicitacaoResponseDTO {
    private Long id;
    private String orgao;
    private String numero;
    private String dataPregao;
    private String objeto;
    private String status;
    private LocalDateTime dataRetorno;
    private boolean temPdf;
    private String urlPdf;

    public LicitacaoResponseDTO(Licitacao licitacao) {
        this.id = licitacao.getId();
        this.orgao = licitacao.getOrgao();
        this.numero = licitacao.getNumero();
        this.dataPregao = licitacao.getDataPregao();
        this.objeto = licitacao.getObjeto();
        this.status = licitacao.getStatus();
        this.dataRetorno = licitacao.getDataRetorno();
        this.temPdf = licitacao.isTemPdf();
        this.urlPdf = licitacao.getUrlPdf();
    }

    public Long getId() { return id; }
    public String getOrgao() { return orgao; }
    public String getNumero() { return numero; }
    public String getDataPregao() { return dataPregao; }
    public String getObjeto() { return objeto; }
    public String getStatus() { return status; }
    public LocalDateTime getDataRetorno() { return dataRetorno; }
    public boolean isTemPdf() { return temPdf; }
    public String getUrlPdf() { return urlPdf; }
}