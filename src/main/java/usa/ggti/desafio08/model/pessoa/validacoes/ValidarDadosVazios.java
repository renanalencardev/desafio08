package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.DadosCadastroPessoaDto;

@Component
public class ValidarDadosVazios implements ValidadorCadastroDePessoa{
    @Override
    public void validar(DadosCadastroPessoaDto dados) {
        if(dados.nome().isEmpty()){
            throw new ValidacaoPessoaException("O campo nome não pode ser vazio.");
        }
        if(dados.email().isEmpty()){
            throw new ValidacaoPessoaException("O campo email não pode ser vazio.");
        }
        if(dados.idade().isEmpty()){
            throw new ValidacaoPessoaException("A campo idade não pode ser vazio.");
        }
    }
}
