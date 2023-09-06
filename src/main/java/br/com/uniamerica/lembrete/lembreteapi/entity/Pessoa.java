package br.com.uniamerica.lembrete.lembreteapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pessoa", schema = "lembrete")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter @Setter
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Getter @Setter
    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "pessoas")
    private List<Lembrete> lembretes = new ArrayList<>();

    public Pessoa(){}

    public Pessoa(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

}
