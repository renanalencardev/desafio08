package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.DadosCadastroPessoaDto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ValidarSeSomenteLetrasNoNome implements ValidadorCadastroDePessoa{
    @Override
    public void validar(DadosCadastroPessoaDto dados) {
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        Matcher matcher = pattern.matcher(dados.nome());
        if(!matcher.matches()){
            throw new ValidacaoPessoaException("O nome da pessoa não pode conter números");
        }
    }
}
