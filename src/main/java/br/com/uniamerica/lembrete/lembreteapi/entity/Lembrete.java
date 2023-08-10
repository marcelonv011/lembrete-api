package br.com.uniamerica.lembrete.lembreteapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lembrete_table", schema = "lembrete")
public class Lembrete {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;

    @Getter @Setter
    @Column(name = "data", nullable = false)
    private LocalDateTime data;

    @Getter @Setter
    @ManyToMany
    @JoinTable(
            name = "Lembrete_pessoas",
            joinColumns = @JoinColumn(name = "lembrete_id"),
            inverseJoinColumns = @JoinColumn(name = "pessoa_id")
    )
    private List<Pessoa> pessoas = new ArrayList<>();

    public Lembrete(){}

    public Lembrete(Long id, String nome, LocalDateTime data, List<Pessoa> pessoas) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.pessoas = pessoas;
    }
}
