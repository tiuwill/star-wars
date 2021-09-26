package br.com.star.wars.service;

import br.com.star.wars.domain.Localizacao;
import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.dto.LocalizacaoDTO;
import br.com.star.wars.domain.dto.RebeldeDTO;
import br.com.star.wars.exception.RebeldeNaoEncontradoException;
import br.com.star.wars.repository.LocalizacaoRepository;
import br.com.star.wars.repository.RebeldeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class RebeldeService {

    private final RebeldeRepository rebeldeRepository;
    private final LocalizacaoRepository localizacaoRepository;

    @Autowired
    public RebeldeService(RebeldeRepository rebeldeRepository, LocalizacaoRepository localizacaoRepository) {
        this.rebeldeRepository = rebeldeRepository;
        this.localizacaoRepository = localizacaoRepository;
    }

    public Page<RebeldeDTO> listar(Pageable paginacao) {
        return rebeldeRepository.findAll(paginacao).map(RebeldeDTO::of);
    }

    public void registrar(RebeldeDTO rebeldeDTO) {
        Localizacao localizacao = localizacaoRepository.save(Localizacao.of(rebeldeDTO.getLocalizacao()));
        rebeldeRepository.save(Rebelde.of(localizacao,rebeldeDTO));
    }

    public RebeldeDTO buscarRebelde(Long id) {
        return RebeldeDTO.of(buscar(id));
    }

    public void atualizarLocalizacao(Long id, LocalizacaoDTO localizacaoDTO) {
        Rebelde rebelde = buscar(id);
        Localizacao novaLocalizacao = Localizacao.of(rebelde.getLocalizacao().getId(), localizacaoDTO);
        localizacaoRepository.save(novaLocalizacao);
    }

    private Rebelde buscar(Long id) {
        return rebeldeRepository.findById(id)
                .orElseThrow(RebeldeNaoEncontradoException::new);
    }
}
