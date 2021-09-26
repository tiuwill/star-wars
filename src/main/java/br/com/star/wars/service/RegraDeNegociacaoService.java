package br.com.star.wars.service;

import br.com.star.wars.domain.dto.ItemDTO;
import br.com.star.wars.exception.PontuacaoNaoEquivalenteException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegraDeNegociacaoService {

    private final ItemService itemService;

    public RegraDeNegociacaoService(ItemService itemService) {
        this.itemService = itemService;
    }

    public void validarNegociacao(List<ItemDTO> itemsPrimeiroNegociante, List<ItemDTO> itemsSegundoNegociante) {
        List<ItemDTO> itemsCheckadosPrimeiroNegociante = itemService.checkarSeItemsSaoValidos(itemsPrimeiroNegociante);
        List<ItemDTO> itemsCheckadosSegundoNegociante = itemService.checkarSeItemsSaoValidos(itemsSegundoNegociante);

        Integer pontosPrimeiro = ItemDTO.calcularPontuacao(itemsCheckadosPrimeiroNegociante);
        Integer pontosSegundo = ItemDTO.calcularPontuacao(itemsCheckadosSegundoNegociante);

        if(!pontosPrimeiro.equals(pontosSegundo)) throw new PontuacaoNaoEquivalenteException();
    }

}
