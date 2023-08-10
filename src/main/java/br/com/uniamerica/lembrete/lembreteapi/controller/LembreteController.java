package br.com.uniamerica.lembrete.lembreteapi.controller;

import br.com.uniamerica.lembrete.lembreteapi.entity.Lembrete;
import br.com.uniamerica.lembrete.lembreteapi.repository.LembreteRepository;
import br.com.uniamerica.lembrete.lembreteapi.service.LembreteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lembretes")
public class LembreteController {
    @Autowired
    private LembreteService lembreteService;

    @Autowired
    private LembreteRepository lembreteRepository;

    @GetMapping()
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.lembreteRepository.findAll());
    }

    @GetMapping("/buscarnome/{nomePessoa}")
    public ResponseEntity<?> buscarLembretesPorNomePessoa(@PathVariable String nomePessoa) {
        List<Lembrete> lembretes = lembreteService.buscarLembretesPorNomePessoa(nomePessoa);

        if (lembretes.isEmpty()) {
            return ResponseEntity.badRequest().body("Ningún valor encontrado.");
        } else {
            return ResponseEntity.ok(lembretes);
        }
    }

    @GetMapping
    public ResponseEntity<?> findByIdRequest(
            @RequestParam("id") final long id
    ){
        final Lembrete lembrete = this.lembreteRepository.findById(id).orElse(null);
        return lembrete == null
                ? ResponseEntity.badRequest().body("Ningun valor encontrado.")
                : ResponseEntity.ok(lembrete);
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Lembrete lembrete){
        try {
            this.lembreteService.cadastraLembrete(lembrete);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable("id") final Long id,
            @RequestBody final Lembrete lembrete
    ) {
        try {
            this.lembreteService.atualizaLembrete(id, lembrete);
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error:" + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("error:" + e.getMessage());
        }
        return ResponseEntity.ok("Registro atualizado com sucesso");
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestParam("id") final Long id){
        final Lembrete lembrete = this.lembreteRepository.findById(id).orElse(null);
        try{
            this.lembreteRepository.delete(lembrete);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Nao pode excluir");
        }
    }
}
