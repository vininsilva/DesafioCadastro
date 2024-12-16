package br.com.CadastroBanco.service.impl;

import br.com.CadastroBanco.model.Pessoa;
import br.com.CadastroBanco.repository.CadastroRepository;
import br.com.CadastroBanco.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroServiceImpl implements CadastroService {

    @Autowired
    public CadastroRepository cadastroRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        if (cadastroRepository.findByCpf(pessoa.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }
        return cadastroRepository.save(pessoa);
    }

    public List<Pessoa> listarPessoas() {
        return cadastroRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(Integer id) {
        return cadastroRepository.findById(id);
    }

    public Pessoa atualizarPessoa(Integer id, Pessoa pessoaAtualizada) {
        Pessoa pessoa = cadastroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada."));
        pessoa.setNome(pessoaAtualizada.getNome());
        pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoa.setTelefone(pessoaAtualizada.getTelefone());
        pessoa.setEndereco(pessoaAtualizada.getEndereco());
        return cadastroRepository.save(pessoa);
    }

    public void deleterPessoa(Integer id) {
        if (cadastroRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Pessoa não encontrada.");
        }
        cadastroRepository.deleteById(id);
    }
}
