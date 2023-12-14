package usa.ggti.desafio08.domain.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.domain.pessoa.DadosCadastroPessoaDto;

@Component
public class ValidarTamanhoDoNomeDaPessoa implements ValidadorCadastroDePessoa{

    public void validar(DadosCadastroPessoaDto dados){
        if(dados.nome().length() < 4){
            throw new ValidacaoPessoaException("O nome deve ter mais de 3 caracteres");
        }
    }
}
