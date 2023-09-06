package br.com.uniamerica.lembrete.lembreteapi.service;

import br.com.uniamerica.lembrete.lembreteapi.entity.Pessoa;
import br.com.uniamerica.lembrete.lembreteapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa cadastraPessoa(Pessoa pessoa){

        return this.pessoaRepository.save(pessoa);

    }

    @Transactional
    public void atualizaPessoa(final Long id, Pessoa pessoa){

        final Pessoa pessoaBanco = this.pessoaRepository.findById(id).orElse(null);

        this.pessoaRepository.save(pessoa);
    }

}
