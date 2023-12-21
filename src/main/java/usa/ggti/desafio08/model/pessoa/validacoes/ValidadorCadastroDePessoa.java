package usa.ggti.desafio08.model.pessoa.validacoes;

import usa.ggti.desafio08.model.pessoa.dtos.DadosCadastroPessoaDto;

public interface ValidadorCadastroDePessoa {
    void validar(DadosCadastroPessoaDto dados);

}
