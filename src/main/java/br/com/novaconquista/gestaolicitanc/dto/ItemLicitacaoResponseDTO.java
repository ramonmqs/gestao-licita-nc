package br.com.novaconquista.gestaolicitanc.dto;

import br.com.novaconquista.gestaolicitanc.model.ItemLicitacao;
import java.math.BigDecimal;

public record ItemLicitacaoResponseDTO(
        Long id,
        Integer numeroItem,
        String descricaoExataEdital,
        BigDecimal valorOriginal,
        BigDecimal valorLimite // O sistema devolverá o teto já calculado: original + 40%
) {
    public ItemLicitacaoResponseDTO(ItemLicitacao item) {
        this(
                item.getId(),
                item.getNumeroItem(),
                item.getDescricaoExataEdital(),
                item.getValorOriginal(),
                item.getValorLimite()
        );
    }
}