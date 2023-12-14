package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.DadosCadastroPessoaDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidarIdade implements ValidadorCadastroDePessoa{
    @Override
    public void validar(DadosCadastroPessoaDto dados) {
        Pattern pattern = Pattern.compile("^(1[89]|[2-5]\\d|6[0-5])$");
        Matcher matcher = pattern.matcher(dados.idade());
        if(!matcher.matches()){
            throw new ValidacaoPessoaException("A idade deve ser acima de 17 e abaixo de 66 anos");
        }
    }
}
