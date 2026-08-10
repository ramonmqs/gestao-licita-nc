package br.com.novaconquista.gestaolicitanc.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data //
@Entity //
@Table(name = "licitacoes")
public class Licitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "numero_pregao", nullable = false)
    private String numeroPregao;

    @Column(nullable = false)
    private String cidade;

    @Column(name = "data_certame", nullable = false)
    private LocalDateTime dataCertame;

    @Column(nullable = false)
    private String modalidade;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String objeto;

    @Column(name = "portal_licitacao")
    private String portalLicitacao;

    @Column(name = "status_interno")
    private String statusInterno = "AGUARDANDO_PDF";

    @Column(name = "url_edital_pdf")
    private String urlEditalPdf;

    @Column(name = "participar")
    private Boolean participar;
}