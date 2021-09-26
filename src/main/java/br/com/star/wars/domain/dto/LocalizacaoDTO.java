package br.com.star.wars.domain.dto;

import br.com.star.wars.domain.Localizacao;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class LocalizacaoDTO {

    private Long id;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitutde;
    @NotNull
    @NotBlank
    private String nome;
    @NotNull
    @NotBlank
    private String galaxia;
    @NotNull
    @NotBlank
    private String base;


    public LocalizacaoDTO(Long id, Double latitude, Double longitutde, String nome, String galaxia, String base) {
        this.id = id;
        this.latitude = latitude;
        this.longitutde = longitutde;
        this.nome = nome;
        this.galaxia = galaxia;
        this.base = base;
    }

    public static LocalizacaoDTO of(Localizacao localizacao) {
        return new LocalizacaoDTO(
                localizacao.getId(),
                localizacao.getLatitude(),
                localizacao.getLongitude(),
                localizacao.getNome(),
                localizacao.getGalaxia(),
                localizacao.getBase()
        );
    }

    public static LocalizacaoDTO of(Long id, Localizacao localizacao) {
        return new LocalizacaoDTO(
                id,
                localizacao.getLatitude(),
                localizacao.getLongitude(),
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
