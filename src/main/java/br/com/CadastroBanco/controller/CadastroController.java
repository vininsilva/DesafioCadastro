package br.com.CadastroBanco.controller;

import br.com.CadastroBanco.model.Pessoa;
import br.com.CadastroBanco.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastro")
public class CadastroController {

    @Autowired
    public CadastroService cadastroService;

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(cadastroService.criarPessoa(pessoa));
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        return ResponseEntity.ok(cadastroService.listarPessoas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Integer id) {
        return cadastroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Integer id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(cadastroService.atualizarPessoa(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Integer id) {
        cadastroService.deleterPessoa(id);
        return ResponseEntity.noContent().build();
    }
}
