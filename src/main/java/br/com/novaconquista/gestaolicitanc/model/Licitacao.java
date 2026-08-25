package br.com.novaconquista.gestaolicitanc.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "licitacoes")
public class Licitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orgao;

    @Column(name = "cidade")
    private String cidade;

    private String numero;

    // Mapeia dataPregao diretamente para a coluna obrigatória antiga data_certame
    @Column(name = "data_certame")
    private String dataPregao;

    @Column(columnDefinition = "TEXT")
    private String objeto;

    private String status = "pendente_pdf";
    private LocalDateTime dataRetorno;
    private boolean temPdf = false;
    private String urlPdf;

    public Licitacao() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrgao() { return orgao; }
    public void setOrgao(String orgao) {
        this.orgao = orgao;
        this.cidade = orgao;
    }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getDataPregao() { return dataPregao; }
    public void setDataPregao(String dataPregao) { this.dataPregao = dataPregao; }

    public String getObjeto() { return objeto; }
    public void setObjeto(String objeto) { this.objeto = objeto; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getDataRetorno() { return dataRetorno; }
    public void setDataRetorno(LocalDateTime dataRetorno) { this.dataRetorno = dataRetorno; }

    public boolean isTemPdf() { return temPdf; }
    public void setTemPdf(boolean temPdf) { this.temPdf = temPdf; }

    public String getUrlPdf() { return urlPdf; }
    public void setUrlPdf(String urlPdf) { this.urlPdf = urlPdf; }
}