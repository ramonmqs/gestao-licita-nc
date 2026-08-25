package br.com.novaconquista.gestaolicitanc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "itens_licitacao")
public class ItemLicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numero;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "licitacao_id")
    private Licitacao licitacao;

    public ItemLicitacao() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNumero() { return numero; }
    public void setNumero(Integer numero) { this.numero = numero; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public Licitacao getLicitacao() { return licitacao; }
    public void setLicitacao(Licitacao licitacao) { this.licitacao = licitacao; }
}