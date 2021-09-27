package br.com.star.wars.service;

import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.dto.ItemDTO;
import br.com.star.wars.domain.dto.NegociacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NegociacaoService {

    private final RebeldeService rebeldeService;
    private final RegraDeNegociacaoService regraDeNegociacaoService;

    @Autowired
    public NegociacaoService(RebeldeService rebeldeService,
                             RegraDeNegociacaoService regraDeNegociacaoService) {
        this.regraDeNegociacaoService = regraDeNegociacaoService;
        this.rebeldeService = rebeldeService;
    }

    @Transactional
    public void negociar(NegociacaoDTO negociacaoDTO) {
        Rebelde primeiroNegociante = rebeldeService.buscar(negociacaoDTO.getIdPrimieroNegociante());
        Rebelde segundoNegociante = rebeldeService.buscar(negociacaoDTO.getIdSegundoNegociante());
        List<ItemDTO> itemsPrimeiroNegociante = negociacaoDTO.getItemsPrimeiroNegociante();
        List<ItemDTO> itemsSegundoNegociante = negociacaoDTO.getItemsSegundoNegociante();

        regraDeNegociacaoService.validarNegociacao(itemsPrimeiroNegociante, itemsSegundoNegociante);

        primeiroNegociante.atualizarInventario(itemsPrimeiroNegociante, itemsSegundoNegociante);
        rebeldeService.salvar(primeiroNegociante);

        segundoNegociante.atualizarInventario(itemsSegundoNegociante, itemsPrimeiroNegociante);
        rebeldeService.salvar(segundoNegociante);
    }
}
