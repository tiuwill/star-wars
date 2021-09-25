package br.com.star.wars.domain;

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


    public ReporteDeTraicao(Long id, Rebelde reporter, Rebelde reportado, LocalDateTime dataDoReporte) {
        this.id = id;
        this.reporter = reporter;
        this.reportado = reportado;
        this.dataDoReporte = dataDoReporte;
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


