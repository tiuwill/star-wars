package br.com.star.wars.domain.dto;

import javax.validation.constraints.NotNull;

public class NegocianteDTO {

    @NotNull
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
