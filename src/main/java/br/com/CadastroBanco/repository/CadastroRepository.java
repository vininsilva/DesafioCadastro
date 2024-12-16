package br.com.CadastroBanco.repository;

import br.com.CadastroBanco.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Pessoa, Integer> {

    boolean findByCpf(String cpf);
}