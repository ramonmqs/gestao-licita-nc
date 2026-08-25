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

    @Column(columnDefinition = "TEXT")
    private String objeto;

    // Status padrão sempre começa aguardando o seu PDF
    private String status = "pendente_pdf";

    // Data e Hora que você vai configurar para o retorno
    private LocalDateTime dataRetorno;

    // Controle da bolinha verde do Marivaldo
    private boolean temPdf = false;

    // Preparando o terreno para a AWS S3
    private String urlPdf;

    // Um pregão tem vários itens. Se apagar o pregão, apaga os itens dele.
    @OneToMany(mappedBy = "licitacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemLicitacao> itens = new ArrayList<>();

    // Construtor vazio (obrigatório do JPA)
    public Licitacao() {}

    // ==========================================
    // GETTERS E SETTERS
    // ==========================================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrgao() { return orgao; }
    public void setOrgao(String orgao) { this.orgao = orgao; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

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

    public String getDataPregao() { return dataPregao; }
    public void setDataPregao(String dataPregao) { this.dataPregao = dataPregao; }

    public List<ItemLicitacao> getItens() { return itens; }
    public void setItens(List<ItemLicitacao> itens) { this.itens = itens; }
}