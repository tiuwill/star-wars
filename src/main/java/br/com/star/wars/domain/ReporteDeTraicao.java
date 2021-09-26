package br.com.star.wars.domain;

import br.com.star.wars.domain.dto.RebeldeDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ReporteDeTraicao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_REPORTER")
    private Rebelde reporter;

    @ManyToOne
    @JoinColumn(name = "ID_REPORTADO")
    private Rebelde reportado;

    private LocalDateTime dataDoReporte;


    public ReporteDeTraicao(Rebelde reporter, Rebelde reportado) {
        this.reporter = reporter;
        this.reportado = reportado;
    }

    public static ReporteDeTraicao of(RebeldeDTO reporter, RebeldeDTO reportado) {
        return new ReporteDeTraicao(
                Rebelde.of(reporter),
                Rebelde.of(reportado)
        );
    }

    @PrePersist
    public void prePersist() {
        if(this.dataDoReporte == null) {
            this.dataDoReporte = LocalDateTime.now();
        }
    }


    public Long getId() {
        return id;
    }

    public Rebelde getReporter() {
        return reporter;
    }

    public Rebelde getReportado() {
        return reportado;
    }


    public LocalDateTime getDataDoReporte() {
        return dataDoReporte;
    }
}


