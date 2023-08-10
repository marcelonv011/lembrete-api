package br.com.uniamerica.lembrete.lembreteapi.repository;

import br.com.uniamerica.lembrete.lembreteapi.entity.Lembrete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LembreteRepository extends JpaRepository<Lembrete, Long> {

    List<Lembrete> findByPessoas_Nome(String nomePessoa);

}
