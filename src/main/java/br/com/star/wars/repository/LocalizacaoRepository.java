package br.com.star.wars.repository;

import br.com.star.wars.domain.Localizacao;
import org.springframework.data.repository.CrudRepository;

public interface LocalizacaoRepository extends CrudRepository<Localizacao, Long> {
}
