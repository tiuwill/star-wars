package br.com.star.wars.domain;

import br.com.star.wars.domain.dto.LocalizacaoDTO;

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

    private Double longitude;

    private String nome;

    private String galaxia;

    private String base;

    public Localizacao() {
    }

    public Localizacao(Long id, Double latitude, Double longitude, String nome, String galaxia, String base) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nome = nome;
        this.galaxia = galaxia;
        this.base = base;
    }

    public static Localizacao of(LocalizacaoDTO localizacao) {
        return new Localizacao(
                null,
                localizacao.getLatitude(),
                localizacao.getLongitutde(),
                localizacao.getNome(),
                localizacao.getGalaxia(),
                localizacao.getBase()
        );
    }

    public static Localizacao of(Long id, LocalizacaoDTO localizacao) {
        return new Localizacao(
                id,
                localizacao.getLatitude(),
                localizacao.getLongitutde(),
                localizacao.getNome(),
                localizacao.getGalaxia(),
                localizacao.getBase()
        );
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
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
