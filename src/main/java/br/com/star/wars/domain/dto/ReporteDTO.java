package br.com.star.wars.domain.dto;

import br.com.star.wars.domain.ReporteDeTraicao;

import javax.validation.constraints.NotNull;

public class ReporteDTO {

    @NotNull
    private Long reporter;
    @NotNull
    private Long reportado;

    public ReporteDTO(Long reporter, Long reportado) {
        this.reporter = reporter;
        this.reportado = reportado;
    }

    public static ReporteDTO of(ReporteDeTraicao reporteDeTraicao) {
        return new ReporteDTO(
                reporteDeTraicao.getReporter().getId(),
                reporteDeTraicao.getReportado().getId()
        );
    }

    public Long getReporter() {
        return reporter;
    }

    public Long getReportado() {
        return reportado;
    }
}
