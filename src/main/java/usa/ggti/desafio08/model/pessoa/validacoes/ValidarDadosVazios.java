package usa.ggti.desafio08.model.pessoa.validacoes;

import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.dtos.DadosCadastroPessoaDto;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidarDadosVazios implements ValidadorCadastroDePessoa{
    private List<String> erros = new ArrayList<>();
    @Override
    public void validar(DadosCadastroPessoaDto dados) {
        if(dados.nome().isEmpty()){
            erros.add("O campo nome não pode ser vazio.");
        }
        if(dados.email().isEmpty()){
            erros.add("O campo email não pode ser vazio.");
        }
        if(dados.idade().isEmpty()){
            erros.add("A campo idade não pode ser vazio.");
        }
    }
    public List<String> getErros() {
        return erros;
    }
}
