package br.com.star.wars.domain.dto;

import br.com.star.wars.domain.Genero;
import br.com.star.wars.domain.Rebelde;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

public class RebeldeDTO {

    private Long id;

    @NotNull
    @NotEmpty
    @NotEmpty
    @Length(min = 1, max = 255)
    private String nome;

    @NotNull
    @Positive
    private Integer idade;

    @NotNull
    private Genero genero;

    @NotNull
    private LocalizacaoDTO localizacao;

    @Valid
    @NotEmpty
    @NotNull
    private List<ItemDTO> items;

    public RebeldeDTO(Long id, String nome, Integer idade, Genero genero, LocalizacaoDTO localizacao) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.localizacao = localizacao;
    }

    public static RebeldeDTO of(Rebelde rebelde) {
        return new RebeldeDTO(
                rebelde.getId(),
                rebelde.getNome(),
                rebelde.getIdade(),
                rebelde.getGenero(),
                LocalizacaoDTO.of(rebelde.getLocalizacao())
        );
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

    public LocalizacaoDTO getLocalizacao() {
        return localizacao;
    }

    public List<ItemDTO> getItems() {
        return items;
    }
}
