package br.com.CadastroBanco.service;

import br.com.CadastroBanco.model.Pessoa;

import java.util.List;
import java.util.Optional;

public interface CadastroService {

    Pessoa criarPessoa(Pessoa pessoa);

    List<Pessoa> listarPessoas();

    Optional<Pessoa> buscarPorId(Integer id);

    Pessoa atualizarPessoa(Integer id, Pessoa pessoa);

    void deleterPessoa(Integer id);

    Optional<Pessoa> buscarPorCpf(String cpf);
}
