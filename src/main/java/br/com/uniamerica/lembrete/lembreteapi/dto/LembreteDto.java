package br.com.uniamerica.lembrete.lembreteapi.dto;

import br.com.uniamerica.lembrete.lembreteapi.entity.Pessoa;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LembreteDto {

    private Long id;

    private String nome;

    private LocalDateTime data;

    private List<Pessoa> pessoas = new ArrayList<>();

    public LembreteDto(){}

    public LembreteDto(Long id, String nome, LocalDateTime data, List<Pessoa> pessoas) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.pessoas = pessoas;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }
}
