package br.com.star.wars.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double latitude;

    private Double longitutde;

    private String nome;

    private String galaxia;

    private String base;


    public Localizacao(Long id, Double latitude, Double longitutde, String nome, String galaxia, String base) {
        this.id = id;
        this.latitude = latitude;
        this.longitutde = longitutde;
        this.nome = nome;
        this.galaxia = galaxia;
        this.base = base;
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitutde() {
        return longitutde;
    }

    public String getNome() {
        return nome;
    }

    public String getGalaxia() {
        return galaxia;
    }

    public String getBase() {
        return base;
    }
}
