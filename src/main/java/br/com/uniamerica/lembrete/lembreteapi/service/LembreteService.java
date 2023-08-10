package br.com.uniamerica.lembrete.lembreteapi.service;

import br.com.uniamerica.lembrete.lembreteapi.entity.Lembrete;
import br.com.uniamerica.lembrete.lembreteapi.entity.Pessoa;
import br.com.uniamerica.lembrete.lembreteapi.repository.LembreteRepository;
import br.com.uniamerica.lembrete.lembreteapi.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LembreteService {

    @Autowired
    private LembreteRepository lembreteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Lembrete> buscarLembretesPorNomePessoa(String nomePessoa) {
        return lembreteRepository.findByPessoas_Nome(nomePessoa);
    }

    public Pessoa buscarPessoaPorNome(String nomePessoa) {
        return pessoaRepository.findByNome(nomePessoa);
    }
     @Transactional
    public void cadastraLembrete(Lembrete lembrete){
         this.lembreteRepository.save(lembrete);

     }

     @Transactional
    public void atualizaLembrete(final Long id, Lembrete lembrete){

         final Lembrete lembreteBanco = this.lembreteRepository.findById(id).orElse(null);

         this.lembreteRepository.save(lembrete);

     }

}
