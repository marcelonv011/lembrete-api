package br.com.uniamerica.lembrete.lembreteapi.controller;

import br.com.uniamerica.lembrete.lembreteapi.entity.Pessoa;
import br.com.uniamerica.lembrete.lembreteapi.repository.PessoaRepository;
import br.com.uniamerica.lembrete.lembreteapi.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping()
    public ResponseEntity<?> listaCompleta(){
        return ResponseEntity.ok(this.pessoaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(
            @PathVariable("id") final Long id
    ){
        final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
        return pessoa == null
                ? ResponseEntity.badRequest().body("Ningun valor encontrado.")
                : ResponseEntity.ok(pessoa);
    }
    @GetMapping
    public ResponseEntity<?> findByIdRequest(
            @RequestParam("id") final long id
    ){
        final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
        return pessoa == null
                ? ResponseEntity.badRequest().body("Ningun valor encontrado.")
                : ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public  ResponseEntity<?> cadastrar(@RequestBody final Pessoa pessoa){
        try {
            this.pessoaService.cadastraPessoa(pessoa);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok().body("Registro adicionado con exito");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(
            @PathVariable("id") final Long id,
            @RequestBody final Pessoa pessoa
    ) {
        try {
            this.pessoaService.atualizaPessoa(id, pessoa);
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
        final Pessoa pessoa = this.pessoaRepository.findById(id).orElse(null);
        try{
            this.pessoaRepository.delete(pessoa);
            return ResponseEntity.ok("Registro deletado");
        }
        catch(RuntimeException e){
            return ResponseEntity.badRequest().body("Nao pode excluir");
        }
    }

}
