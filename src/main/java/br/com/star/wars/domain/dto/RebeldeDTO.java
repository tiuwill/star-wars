package br.com.star.wars.domain.dto;

import br.com.star.wars.domain.Genero;
import br.com.star.wars.domain.Rebelde;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public RebeldeDTO(Long id, String nome, Integer idade, Genero genero, LocalizacaoDTO localizacao, List<ItemDTO> items) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.localizacao = localizacao;
        this.items = items;
    }


    public static RebeldeDTO of(Rebelde rebelde) {
        return new RebeldeDTO(
            rebelde.getId(),
            rebelde.getNome(),
            rebelde.getIdade(),
            rebelde.getGenero(),
            LocalizacaoDTO.of(rebelde.getLocalizacao()),
            rebelde.getItems().stream().map(ItemDTO::of).collect(Collectors.toList())
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
        if(items == null) return Collections.emptyList();
        return items;
    }
}
