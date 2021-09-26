package br.com.star.wars.controller;

import br.com.star.wars.domain.dto.LocalizacaoDTO;
import br.com.star.wars.domain.dto.RebeldeDTO;
import br.com.star.wars.service.RebeldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;

@RestController
@RequestMapping("/rebelde")
public class RebeldeController {

    private final RebeldeService rebeldeService;

    @Autowired
    public RebeldeController(RebeldeService rebeldeService) {
        this.rebeldeService = rebeldeService;
    }

    @GetMapping
    public Page<RebeldeDTO> listar(
            @PageableDefault(sort = "id", direction = Sort.Direction.DESC, page = 0, size = 10) Pageable paginacao) {
        return rebeldeService.listar(paginacao);
    }

    @GetMapping("/{id}")
    public RebeldeDTO listarRebeldes(@PathParam("id") Long id) {
        return rebeldeService.buscarRebelde(id);
    }

    @PostMapping
    public ResponseEntity<?> registrarRebelder(@Valid @RequestBody RebeldeDTO rebeldeDTO) {
        rebeldeService.registrar(rebeldeDTO);
        return ResponseEntity.created(URI.create("/rebelde")).build();
    }

    @PatchMapping("/{id}/localizacao")
    public ResponseEntity<?> atualizarLocalizacao(@PathParam("id") Long id,
                                     @Valid LocalizacaoDTO localizacaoDTO) {
        rebeldeService.atualizarLocalizacao(id, localizacaoDTO);
        return ResponseEntity.noContent().build();
    }
}
