package br.com.uniamerica.lembrete.lembreteapi;

import br.com.uniamerica.lembrete.lembreteapi.entity.Pessoa;
import br.com.uniamerica.lembrete.lembreteapi.repository.PessoaRepository;
import br.com.uniamerica.lembrete.lembreteapi.service.PessoaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Optional;

@SpringBootTest
public class PessoaTest {

    @MockBean
    PessoaService pessoaService;

    @MockBean
    PessoaRepository pessoaRepository;

    @BeforeEach
    void injectData() {
        Pessoa pessoa = new Pessoa(1L, "Marcelo");

        Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);

        Mockito.when(pessoaRepository.findById(1L)).thenReturn(Optional.of(pessoa));
    }

    @Test
    public void testControllerPessoa() {
        var pessoaTest = pessoaRepository.findById(1L);
        Long id = pessoaTest.get().getId();

        Assertions.assertEquals(pessoaTest, pessoaRepository.findById(id));
    }

    @Test
    public void TestcadastraPessoa() {

        Assertions.assertEquals(pessoaRepository.save(new Pessoa(1L, "Marcelo")), pessoaService.cadastraPessoa(new Pessoa(1L, "Marcelo")));

    }

    @Test
    public void testAtualizarPessoa() {

        Long id = 1L;
        Pessoa personaAtualizada = new Pessoa(id, "Pedro");

        pessoaService.atualizaPessoa(id, personaAtualizada);

        Assertions.assertEquals("Pedro", personaAtualizada.getNome());
    }


}
