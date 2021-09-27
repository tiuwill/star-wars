package br.com.star.wars.repository;

import br.com.star.wars.domain.Rebelde;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RebeldeRepository extends CrudRepository<Rebelde, Long> {

    Page<Rebelde> findByTraidorFalse(Pageable paginacao);

    List<Rebelde> findByTraidorTrue();

    Long countByTraidorTrue();

    Long countByTraidorFalse();

    List<Rebelde> findByTraidorFalse();
}
