package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.infra.exception.ValidacaoCadastroPessoaException;
import usa.ggti.desafio08.model.pessoa.dtos.DadosCadastroPessoaDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarTamanhoDoNomeDaPessoa implements ValidadorCadastroDePessoa{

    public void validar(DadosCadastroPessoaDto dados){
        if(dados.nome().length() < 4){
            throw new ValidacaoCadastroPessoaException("O nome deve ter mais de 3 caracteres");
        }
    }
}
