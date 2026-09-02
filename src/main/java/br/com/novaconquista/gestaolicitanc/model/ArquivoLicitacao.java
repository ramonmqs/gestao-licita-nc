package br.com.novaconquista.gestaolicitanc.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "arquivos_licitacao")
public class ArquivoLicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeArquivo;

    @Lob
    @JsonIgnore // Impede que o arquivo pesado seja carregado na listagem principal
    private byte[] dados;

    @ManyToOne
    @JoinColumn(name = "licitacao_id")
    @JsonIgnore // Evita loop infinito no retorno da API
    private Licitacao licitacao;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeArquivo() { return nomeArquivo; }
    public void setNomeArquivo(String nomeArquivo) { this.nomeArquivo = nomeArquivo; }
    public byte[] getDados() { return dados; }
    public void setDados(byte[] dados) { this.dados = dados; }
    public Licitacao getLicitacao() { return licitacao; }
    public void setLicitacao(Licitacao licitacao) { this.licitacao = licitacao; }
}