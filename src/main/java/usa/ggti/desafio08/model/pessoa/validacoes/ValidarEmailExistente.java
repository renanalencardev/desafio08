package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.DadosCadastroPessoaDto;
import usa.ggti.desafio08.model.pessoa.PessoaRepository;

@Component
public class ValidarEmailExistente implements ValidadorCadastroDePessoa{
    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public void validar(DadosCadastroPessoaDto dados) {
        var pessoa = pessoaRepository.existsByEmail(dados.email());
        if(pessoa){
            throw new ValidacaoPessoaException("Email já cadastrado");
        }

    }
}
