package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.DadosCadastroPessoaDto;

@Component
public class ValidarDadosVaziosOuNulos implements ValidadorCadastroDePessoa{
    @Override
    public void validar(DadosCadastroPessoaDto dados) {
        if(dados.nome().isEmpty()){
            throw new ValidacaoPessoaException("O campo nome não pode ser vazio.");
        }
        if(dados.nome() == null){
            throw new ValidacaoPessoaException("O campo nome não pode ser nulo.");
        }
        if(dados.email().isEmpty()){
            throw new ValidacaoPessoaException("O campo email não pode ser vazio.");
        }
        if(dados.email() == null){
            throw new ValidacaoPessoaException("O campo e-mail não pode ser nulo.");
        }
        if(dados.idade().isEmpty()){
            throw new ValidacaoPessoaException("A campo idade não pode ser vazio.");
        }
        if(dados.idade() == null){
            throw new ValidacaoPessoaException("A campo idade não pode ser nulo.");
        }
    }
}
