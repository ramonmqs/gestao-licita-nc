package br.com.novaconquista.gestaolicitanc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "licitacoes")
public class Licitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgao;
    private String numero;
    private String dataPregao;
    private String modalidade;
    private String portalLicitacao;

    @Column(columnDefinition = "TEXT")
    private String objeto;

    private String status = "pendente_pdf";

    // Novos campos para o acompanhamento de Ramon
    private LocalDateTime dataRetorno;

    @Column(columnDefinition = "TEXT")
    private String diligencia;

    private String urlPdf;

    @OneToMany(mappedBy = "licitacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArquivoLicitacao> arquivos = new ArrayList<>();

    public List<ArquivoLicitacao> getArquivos() { return arquivos; }
    public void setArquivos(List<ArquivoLicitacao> arquivos) { this.arquivos = arquivos; }

    public Licitacao() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrgao() { return orgao; }
    public void setOrgao(String orgao) { this.orgao = orgao; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getDataPregao() { return dataPregao; }
    public void setDataPregao(String dataPregao) { this.dataPregao = dataPregao; }

    public String getModalidade() { return modalidade; }
    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

    public String getPortalLicitacao() { return portalLicitacao; }
    public void setPortalLicitacao(String portalLicitacao) { this.portalLicitacao = portalLicitacao; }

    public String getObjeto() { return objeto; }
    public void setObjeto(String objeto) { this.objeto = objeto; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataRetorno() { return dataRetorno; }
    public void setDataRetorno(LocalDateTime dataRetorno) { this.dataRetorno = dataRetorno; }

    public String getDiligencia() { return diligencia; }
    public void setDiligencia(String diligencia) { this.diligencia = diligencia; }

    public String getUrlPdf() { return urlPdf; }
    public void setUrlPdf(String urlPdf) { this.urlPdf = urlPdf; }

}