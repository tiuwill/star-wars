package br.com.star.wars.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Rebelde {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Integer idade;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private boolean traidor;

    @ManyToMany
    @JoinTable(name = "ITEM_DO_REBELDE",
    joinColumns = @JoinColumn(name = "ID_REBELDE"),
    inverseJoinColumns = @JoinColumn(name = "ID_ITEM"))
    private List<Item> items;

    @OneToOne
    @JoinColumn(name = "localizacao_id", referencedColumnName = "id")
    private Localizacao localizacao;

    public Rebelde(Long id, String nome, Integer idade, Genero genero, boolean traidor) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.traidor = traidor;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Integer getIdade() {
        return idade;
    }

    public Genero getGenero() {
        return genero;
    }

    public boolean isTraidor() {
        return traidor;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }
}
