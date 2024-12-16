package br.com.CadastroBanco.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Endereco {

    private String rua;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;
}
