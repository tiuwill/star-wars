package br.com.star.wars.domain;

import br.com.star.wars.domain.dto.RebeldeDTO;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Rebelde {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private Integer idade;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    private boolean traidor;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ITEM_DO_REBELDE",
    joinColumns = @JoinColumn(name = "ID_REBELDE"),
    inverseJoinColumns = @JoinColumn(name = "ID_ITEM"))
    private List<Item> items;

    @OneToOne
    @JoinColumn(name = "ID_LOCALIZACAO", referencedColumnName = "id")
    private Localizacao localizacao;

    @OneToMany(mappedBy="reporter")
    private List<ReporteDeTraicao> reportesDados;

    @OneToMany(mappedBy="reportado")
    private List<ReporteDeTraicao> reportesRecebidos;

    public Rebelde() {
    }

    public Rebelde(String nome, Integer idade, Genero genero, List<Item> items, Localizacao localizacao) {
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
        this.traidor = false;
        this.items = items;
        this.localizacao = localizacao;
    }

    public static Rebelde of(RebeldeDTO rebeldeDTO) {
        return new Rebelde(
                rebeldeDTO.getNome(),
                rebeldeDTO.getIdade(),
                rebeldeDTO.getGenero(),
                rebeldeDTO.getItems().stream().map(Item::of).collect(Collectors.toList()),
                Localizacao.of(rebeldeDTO.getLocalizacao())
        );
    }

    public static Rebelde of(Localizacao localizacao, RebeldeDTO rebeldeDTO) {
        return new Rebelde(
                rebeldeDTO.getNome(),
                rebeldeDTO.getIdade(),
                rebeldeDTO.getGenero(),
                rebeldeDTO.getItems().stream().map(Item::of).collect(Collectors.toList()),
                localizacao
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

    public boolean isTraidor() {
        return traidor;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public List<ReporteDeTraicao> getReportesDados() {
        return reportesDados;
    }

    public List<ReporteDeTraicao> getReportesRecebidos() {
        return reportesRecebidos;
    }
}
