package br.com.star.wars.service;

import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.ReporteDeTraicao;
import br.com.star.wars.domain.dto.RebeldeDTO;
import br.com.star.wars.domain.dto.ReporteDTO;
import br.com.star.wars.exception.AutoReporteException;
import br.com.star.wars.exception.RebeldeNaoEncontradoException;
import br.com.star.wars.exception.ReporteDeTraicaoDuplicadoException;
import br.com.star.wars.repository.RebeldeRepository;
import br.com.star.wars.repository.ReporteDeTraicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteService {

    private final ReporteDeTraicaoRepository repository;
    private final RebeldeRepository rebeldeRepository;

    @Autowired
    public ReporteService(ReporteDeTraicaoRepository repository,
                          RebeldeRepository rebeldeRepository) {
        this.repository = repository;
        this.rebeldeRepository = rebeldeRepository;
    }

    public void reportar(ReporteDTO reporteDTO) {

        Rebelde reporter = buscarRebelde(reporteDTO.getReporter());
        Rebelde reportado = buscarRebelde(reporteDTO.getReportado());

        if(reporter.getId().equals(reportado.getId())) throw new AutoReporteException(reporter.getNome());

        repository.findByReporterAndReportado(reporter, reportado)
            .ifPresent(x -> {
                throw new ReporteDeTraicaoDuplicadoException(reporter.getNome(), reportado.getNome());
            });

        repository.save(new ReporteDeTraicao(reporter, reportado));
    }

    public Rebelde buscarRebelde(Long id) {
        return rebeldeRepository.findById(id).orElseThrow(RebeldeNaoEncontradoException::new);
    }

}
