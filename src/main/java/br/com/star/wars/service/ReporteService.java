package br.com.star.wars.service;

import br.com.star.wars.domain.ReporteDeTraicao;
import br.com.star.wars.domain.dto.RebeldeDTO;
import br.com.star.wars.exception.AutoReporteException;
import br.com.star.wars.exception.ReporteDeTraicaoDuplicadoException;
import br.com.star.wars.repository.ReporteDeTraicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private final ReporteDeTraicaoRepository repository;

    @Autowired
    public ReporteService(ReporteDeTraicaoRepository repository) {
        this.repository = repository;
    }

    public void reportar(RebeldeDTO reporter, RebeldeDTO reportado) {

        if(reporter.getId().equals(reportado.getId())) throw new AutoReporteException(reporter.getNome());

        repository.findByReporterAndReportado(reporter.getId(), reportado.getId())
            .ifPresent(x -> {
                throw new ReporteDeTraicaoDuplicadoException(reporter.getNome(), reportado.getNome());
            });

        repository.save(ReporteDeTraicao.of(reporter, reportado));
    }
}
