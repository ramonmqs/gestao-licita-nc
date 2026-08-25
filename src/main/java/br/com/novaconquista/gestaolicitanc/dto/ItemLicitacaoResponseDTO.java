package br.com.novaconquista.gestaolicitanc.dto;

import br.com.novaconquista.gestaolicitanc.model.ItemLicitacao;

public class ItemLicitacaoResponseDTO {
    private Long id;
    private Integer numero;
    private String descricao;
    private Integer quantidade;

    public ItemLicitacaoResponseDTO(ItemLicitacao item) {
        this.id = item.getId();
        this.numero = item.getNumero();
        this.descricao = item.getDescricao();
        this.quantidade = item.getQuantidade();
    }

    public Long getId() { return id; }
    public Integer getNumero() { return numero; }
    public String getDescricao() { return descricao; }
    public Integer getQuantidade() { return quantidade; }
}