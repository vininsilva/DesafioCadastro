package br.com.CadastroBanco.repository;

import br.com.CadastroBanco.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroRepository extends JpaRepository<Pessoa, Integer> {

    @Query("SELECT COUNT(p) > 0 FROM Pessoa p WHERE p.cpf = :cpf")
    boolean findByCpf(String cpf);
}