package br.com.star.wars.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   private String descricao;

   private Integer pontos;

   @ManyToMany(mappedBy = "ITEM_DO_REBELDE")
   private List<Rebelde> rebeldes;

    public Item(Long id, String descricao, Integer pontos) {
        this.id = id;
        this.descricao = descricao;
        this.pontos = pontos;
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
