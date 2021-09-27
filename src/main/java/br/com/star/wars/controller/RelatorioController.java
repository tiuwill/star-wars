package br.com.star.wars.controller;

import br.com.star.wars.domain.dto.relatorio.PontosRelatorioDTO;
import br.com.star.wars.domain.dto.relatorio.RebeldesRelatoriosDTO;
import br.com.star.wars.domain.dto.relatorio.TraidoresRelatorioDTO;
import br.com.star.wars.domain.dto.relatorio.RecursosRelatorioDTO;
import br.com.star.wars.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

    private final RelatorioService relatorioService;

    @Autowired
    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/traidores")
    public ResponseEntity<TraidoresRelatorioDTO> calcularTraidores() {
        TraidoresRelatorioDTO relatorioDTO = relatorioService.calcularTraidores();
        return ResponseEntity.ok(relatorioDTO);
    }


    @GetMapping("/rebeldes")
    public ResponseEntity<RebeldesRelatoriosDTO> calcularRebeldes() {
        RebeldesRelatoriosDTO relatorioDTO = relatorioService.calcularRebeldes();
        return ResponseEntity.ok(relatorioDTO);
    }

    @GetMapping("/recursos")
    public ResponseEntity<RecursosRelatorioDTO> calcularRecursos() {
        RecursosRelatorioDTO relatorioDTO = relatorioService.calcularRecursos();
        return ResponseEntity.ok(relatorioDTO);
    }

    @GetMapping("/prontos")
    public ResponseEntity<PontosRelatorioDTO> calcularPontos() {
        PontosRelatorioDTO relatorioDTO = relatorioService.calcularPontos();
        return ResponseEntity.ok(relatorioDTO);
    }
}
