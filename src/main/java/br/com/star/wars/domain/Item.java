package br.com.star.wars.domain;

import br.com.star.wars.domain.dto.ItemDTO;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String descricao;

   private Integer pontos;

   @ManyToMany(mappedBy = "items")
   private List<Rebelde> rebeldes;

    public Item(Long id, String descricao, Integer pontos) {
        this.id = id;
        this.descricao = descricao;
        this.pontos = pontos;
    }

    public static Item of(ItemDTO itemDTO) {
        return new Item(
                itemDTO.getId(),
                itemDTO.getDescricao(),
                itemDTO.getPontos()
        );
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getPontos() {
        return pontos;
    }
}
