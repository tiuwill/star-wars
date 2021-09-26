package br.com.star.wars.repository;

import br.com.star.wars.domain.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Set<Item> findAll();

}
