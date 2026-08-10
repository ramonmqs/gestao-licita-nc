package br.com.novaconquista.gestaolicitanc.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record LicitacaoRequestDTO(
        @NotBlank(message = "O número do pregão é obrigatório")
        String numeroPregao,

        @NotBlank(message = "A cidade é obrigatória")
        String cidade,

        @NotNull(message = "A data do certame é obrigatória")
        LocalDateTime dataCertame,

        @NotBlank(message = "A modalidade é obrigatória")
        String modalidade,

        @NotBlank(message = "O objeto é obrigatório")
        String objeto
) {}