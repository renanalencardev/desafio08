package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.DadosCadastroPessoaDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Component
public class ValidarEmail implements ValidadorCadastroDePessoa{
    @Override
    public void validar(DadosCadastroPessoaDto dados) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(dados.email());
        if(!matcher.matches()){
            throw new ValidacaoPessoaException("O e-mail está em um formato inválido.");
        }
    }
}
