package br.com.star.wars.service;

import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.ReporteDeTraicao;
import br.com.star.wars.domain.dto.ReporteDTO;
import br.com.star.wars.exception.AutoReporteException;
import br.com.star.wars.exception.ReporteDeTraicaoDuplicadoException;
import br.com.star.wars.repository.ReporteDeTraicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private final ReporteDeTraicaoRepository repository;
    private final RebeldeService rebeldeService;

    @Autowired
    public ReporteService(ReporteDeTraicaoRepository repository,
                          RebeldeService rebeldeService) {
        this.repository = repository;
        this.rebeldeService = rebeldeService;
    }

    public void reportar(ReporteDTO reporteDTO) {
        Rebelde reporter = rebeldeService.buscar(reporteDTO.getReporter());
        Rebelde reportado = rebeldeService.buscar(reporteDTO.getReportado());

        if(reporter.getId().equals(reportado.getId())) throw new AutoReporteException(reporter.getNome());

        repository.findByReporterAndReportado(reporter, reportado)
            .ifPresent(x -> {
                throw new ReporteDeTraicaoDuplicadoException(reporter.getNome(), reportado.getNome());
            });

        repository.save(new ReporteDeTraicao(reporter, reportado));
        if(reportado.seTornouTraidor()) rebeldeService.salvar(reportado);
    }
}
