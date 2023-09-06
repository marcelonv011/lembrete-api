package br.com.uniamerica.lembrete.lembreteapi;

import br.com.uniamerica.lembrete.lembreteapi.entity.Lembrete;
import br.com.uniamerica.lembrete.lembreteapi.entity.Pessoa;
import br.com.uniamerica.lembrete.lembreteapi.repository.LembreteRepository;
import br.com.uniamerica.lembrete.lembreteapi.repository.PessoaRepository;
import br.com.uniamerica.lembrete.lembreteapi.service.LembreteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class LembreteTest {

    @MockBean
    LembreteService lembreteService;

    @MockBean
    LembreteRepository lembreteRepository;
    @MockBean
    PessoaRepository pessoaRepository;

    @BeforeEach
    void injectData() {
        Pessoa pessoa1 = new Pessoa(1L, "Persona1");

        List<Pessoa> personas = new ArrayList<>();
        personas.add(pessoa1);

        LocalDateTime localDateTime = LocalDateTime.parse("2023-09-06T15:30:00");
        Lembrete lembrete = new Lembrete(1L, "lembrete1", localDateTime, personas);

        Mockito.when(lembreteRepository.save(lembrete)).thenReturn(lembrete);

        Mockito.when(lembreteRepository.findById(1L)).thenReturn(Optional.of(lembrete));
        Mockito.when(lembreteService.buscarLembretesPorNomePessoa("Persona1")).thenReturn(List.of(lembrete));
    }

    @Test
    public void testBuscarLembretesPorNomePessoa() {
        // Llamar al método que se está probando
        List<Lembrete> lembretes = lembreteService.buscarLembretesPorNomePessoa("Persona1");

        // Verificar que la lista de lembretes tenga un tamaño de 1
        Assertions.assertEquals(1, lembretes.size());

        // Verificar que el primer lembrete en la lista tenga el nombre esperado
        Assertions.assertEquals("lembrete1", lembretes.get(0).getNome());
    }


    @Test
    public void testControllerLembrete() {
        var lembreteTest = lembreteRepository.findById(1L);
        Long id = lembreteTest.get().getId();

        Assertions.assertEquals(lembreteTest, lembreteRepository.findById(id));
    }

    @Test
    public void TestcadastraLembrete() {

        LocalDateTime localDateTime = LocalDateTime.parse("2023-09-06T15:30:00");

        Pessoa pessoa1 = new Pessoa(1L, "Persona1");

        List<Pessoa> personas = new ArrayList<>();
        personas.add(pessoa1);

        Assertions.assertEquals(lembreteRepository.save(new Lembrete(1L, "lembrete1", localDateTime, personas)), lembreteService.cadastraLembrete(new Lembrete(1L, "lembrete1", localDateTime, personas)));

    }

    @Test
    public void testAtualizarLembrete() {

        Long id = 1L;

        LocalDateTime localDateTime = LocalDateTime.parse("2023-09-06T15:30:00");

        Pessoa pessoa1 = new Pessoa(1L, "Persona1");

        List<Pessoa> personas = new ArrayList<>();
        personas.add(pessoa1);

        Lembrete lembreteAtualizada = new Lembrete(id, "lembrete2904", localDateTime, personas);

        lembreteService.atualizaLembrete(id, lembreteAtualizada);

        Assertions.assertEquals("lembrete2904", lembreteAtualizada.getNome());
        Assertions.assertEquals(localDateTime, lembreteAtualizada.getData());
        Assertions.assertEquals(personas, lembreteAtualizada.getPessoas());
    }

}
