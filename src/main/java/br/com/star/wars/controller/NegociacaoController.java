package br.com.star.wars.controller;

import br.com.star.wars.domain.dto.NegociacaoDTO;
import br.com.star.wars.service.NegociacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/negociacao")
public class NegociacaoController {

    private final NegociacaoService negociacaoService;

    @Autowired
    public NegociacaoController(NegociacaoService negociacaoService) {
        this.negociacaoService = negociacaoService;
    }

    @PostMapping
    public ResponseEntity<?> negociar(@Valid @RequestBody NegociacaoDTO negociatnteDTO) {
        negociacaoService.negociar(negociatnteDTO);
        return ResponseEntity.noContent().build();
    }
}
