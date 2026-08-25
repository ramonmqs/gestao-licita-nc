package br.com.novaconquista.gestaolicitanc.dto;

import java.math.BigDecimal;

public record ItemLicitacaoRequestDTO(
        Integer numeroItem,
        String descricaoExataEdital,
        BigDecimal valorOriginal
) {}