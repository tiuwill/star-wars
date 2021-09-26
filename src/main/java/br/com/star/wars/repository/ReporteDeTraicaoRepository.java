package br.com.star.wars.repository;

import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.ReporteDeTraicao;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReporteDeTraicaoRepository extends CrudRepository<ReporteDeTraicao, Long> {

    Optional<ReporteDeTraicao> findByReporterAndReportado(Rebelde reporter, Rebelde reportado);
}
