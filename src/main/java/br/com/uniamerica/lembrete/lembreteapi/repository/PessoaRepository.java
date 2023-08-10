package br.com.uniamerica.lembrete.lembreteapi.repository;

import br.com.uniamerica.lembrete.lembreteapi.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Pessoa findByNome(String nomePessoa);
}
