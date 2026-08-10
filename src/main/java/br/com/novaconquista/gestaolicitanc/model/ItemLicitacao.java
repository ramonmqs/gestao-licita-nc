package br.com.novaconquista.gestaolicitanc.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_licitacao")
@Getter
@Setter
public class ItemLicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numeroItem;

    // Regra de Negócio: Deve ser armazenado exatamente como no edital, sem resumos.
    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricaoExataEdital;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorOriginal;

    // Calculado automaticamente: valorOriginal + 40%
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valorLimite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licitacao_id", nullable = false)
    private Licitacao licitacao;

    // Método que garante a trava matemática dos 40% no momento em que o item é instanciado
    @PrePersist
    @PreUpdate
    public void calcularValorLimite() {
        if (this.valorOriginal != null) {
            BigDecimal multiplicador = new BigDecimal("1.40");
            this.valorLimite = this.valorOriginal.multiply(multiplicador);
        }
    }
}