package br.com.CadastroBanco;

import br.com.CadastroBanco.model.Pessoa;
import br.com.CadastroBanco.repository.CadastroRepository;
import br.com.CadastroBanco.service.CadastroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import static org.mockito.Mockito.when;


public class CadastroServiceTest {

    @Mock
    private CadastroRepository cadastroRepository;

    @InjectMocks
    private CadastroService cadastroService;

    private Pessoa pessoaExistente;
    private Pessoa pessoaAtualizada;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pessoaExistente = new Pessoa();
        pessoaExistente.setId(1);
        pessoaExistente.setCpf("11122233344");
        pessoaExistente.setNome("Vinicius");
        pessoaExistente.setDataNascimento(LocalDate.of(1995, 2, 6));
        pessoaExistente.setTelefone("11111111111");

        pessoaAtualizada = new Pessoa();
        pessoaAtualizada.setNome("Vinicius Silva");
        pessoaAtualizada.setDataNascimento(LocalDate.of(1996, 3, 7));
        pessoaAtualizada.setTelefone("22222222222");
    }

    @Test
    void testAtualizarPessoa_Sucesso() {
//        when(cadastroRepository.findById(1)).thenReturn(java.util.Optional.of(pessoaExistente));
        when(cadastroRepository.save(any(Pessoa.class))).thenReturn(pessoaExistente);

        Pessoa pessoaRetornada = cadastroService.atualizarPessoa(1, pessoaAtualizada);

        verify(cadastroRepository).findById(1);
        verify(cadastroRepository).save(pessoaExistente);

        assertEquals("Ricardo Silva", pessoaRetornada.getNome());
        assertEquals(LocalDate.of(1996, 3, 7), pessoaRetornada.getDataNascimento());
        assertEquals("22222222222", pessoaRetornada.getTelefone());
    }

    @Test
    void testAtualizarPessoa_PessoaNaoEncontrada() {
        when(cadastroRepository.findById(1)).thenReturn(java.util.Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cadastroService.atualizarPessoa(1, pessoaAtualizada);
        });

        assertEquals("Pessoa não encontrada.", exception.getMessage());
    }
}
