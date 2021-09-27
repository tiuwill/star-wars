package br.com.star.wars.repository;

import br.com.star.wars.domain.Rebelde;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface RebeldeRepository extends CrudRepository<Rebelde, Long> {

    Page<Rebelde> findByTraidorFalse(Pageable paginacao);
}
