package br.com.star.wars.controller;

import br.com.star.wars.domain.dto.RebeldeDTO;
import br.com.star.wars.domain.dto.ReporteDTO;
import br.com.star.wars.service.RebeldeService;
import br.com.star.wars.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/reportar")
public class ReporteController {

    private final ReporteService reporteService;
    private final RebeldeService rebeldeService;


    @Autowired
    public ReporteController(ReporteService reporteService, RebeldeService rebeldeService) {
        this.reporteService = reporteService;
        this.rebeldeService = rebeldeService;
    }

    @PostMapping
    public ResponseEntity<?> reportar(@Valid @RequestBody ReporteDTO reporteDTO) {
        RebeldeDTO reporter = rebeldeService.buscarRebelde(reporteDTO.getReporter());
        RebeldeDTO reportado = rebeldeService.buscarRebelde(reporteDTO.getReportado());
        reporteService.reportar(reporter, reportado);
        return ResponseEntity.noContent().build();
    }
}
