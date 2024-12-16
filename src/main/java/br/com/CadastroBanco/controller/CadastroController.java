package br.com.CadastroBanco.controller;

import br.com.CadastroBanco.model.Pessoa;
import br.com.CadastroBanco.service.CadastroService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cadastro")
public class CadastroController {

    @Autowired
    public CadastroService cadastroService;

    @ApiOperation(value = "Cadastrar pessoa")
    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(cadastroService.criarPessoa(pessoa));
    }

    @ApiOperation(value = "Buscar todas as pessoas cadastradas")
    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        return ResponseEntity.ok(cadastroService.listarPessoas());
    }

    @ApiOperation(value = "Buscar cadastro por Id")
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Integer id) {
        return cadastroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Buscar cadastro por CPF")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Pessoa> buscarPorCpf(@PathVariable String cpf) {
        return cadastroService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Atualizar cadastro")
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Integer id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(cadastroService.atualizarPessoa(id, pessoa));
    }

    @ApiOperation(value = "Deletar cadastro")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Integer id) {
        cadastroService.deleterPessoa(id);
        return ResponseEntity.noContent().build();
    }
}

//Exemplo de body para chamada
//{
//        "cpf": "11111111111",
//        "nome": "Vinicius",
//        "dataNascimento": "1995-02-06",
//        "telefone": "111111111111111",
//        "endereco": {
//        "rua": "Rua Teste",
//        "numero": "123",
//        "bairro": "Centro",
//        "cidade": "São Paulo",
//        "estado": "SP",
//        "cep": "01000000"
//        }
//}