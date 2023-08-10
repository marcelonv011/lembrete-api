package br.com.uniamerica.lembrete.lembreteapi.dto;

import br.com.uniamerica.lembrete.lembreteapi.entity.Lembrete;


import java.util.ArrayList;
import java.util.List;

public class PessoaDto {


    private Long id;

    private String nome;

    private List<Lembrete> lembretes = new ArrayList<>();

    public PessoaDto(){}

    public PessoaDto(Long id, String nome, List<Lembrete> lembretes) {
        this.id = id;
        this.nome = nome;
        this.lembretes = lembretes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Lembrete> getLembretes() {
        return lembretes;
    }

    public void setLembretes(List<Lembrete> lembretes) {
        this.lembretes = lembretes;
    }
}
