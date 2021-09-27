package br.com.star.wars.service;

import br.com.star.wars.domain.Rebelde;
import br.com.star.wars.domain.dto.relatorio.PontosRelatorioDTO;
import br.com.star.wars.domain.dto.relatorio.RebeldesRelatoriosDTO;
import br.com.star.wars.domain.dto.relatorio.TraidoresRelatorioDTO;
import br.com.star.wars.domain.dto.relatorio.RecursosRelatorioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RelatorioService {

    private final RebeldeService rebeldeService;

    @Autowired
    public RelatorioService(RebeldeService rebeldeService) {
        this.rebeldeService = rebeldeService;
    }

    public TraidoresRelatorioDTO calcularTraidores() {
        Long traidores = rebeldeService.contarTraidores();
        Long total = rebeldeService.totalDeRegistros();

        Double porcentagem =  (traidores/total) * 100.0;
        return new TraidoresRelatorioDTO(String.format("%.2f", porcentagem));
    }

    public RebeldesRelatoriosDTO calcularRebeldes() {
        Long rebeldes = rebeldeService.contarRebeldes();
        Long total = rebeldeService.totalDeRegistros();

        Double porcentagem =  (rebeldes/total) * 100.0;
        return new RebeldesRelatoriosDTO(String.format("%.2f", porcentagem));
    }

    public RecursosRelatorioDTO calcularRecursos() {
        final Map<String, Double> dados = new HashMap<>();
        List<Rebelde> rebeldes = rebeldeService.listarRebeldes();
        rebeldes.forEach(rebelde -> {
            rebelde.getItems().forEach(item -> {
                if(!dados.containsKey(item.getDescricao())) {
                    dados.put(item.getDescricao(), 1.0);
                } else {
                    dados.put(item.getDescricao(), dados.get(item.getDescricao()) + 1.0);
                }
            });
        });

        dados.forEach((key, value) -> dados.put(key, dados.get(key)/rebeldes.size()));
        return new RecursosRelatorioDTO(dados);
    }


    public PontosRelatorioDTO calcularPontos() {
        List<Rebelde> traidores = this.rebeldeService.listarTraidores();
        Integer pontosPerdidos = 0;
        for (Rebelde traidor: traidores) {
            pontosPerdidos += traidor.getPontuacao();
        }

        return new PontosRelatorioDTO(pontosPerdidos);
    }
}
