package usa.ggti.desafio08.model.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.ggti.desafio08.model.pessoa.validacoes.ValidadorCadastroDePessoa;

import java.util.List;

@Service
public class ServicoCadastroDePessoa {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private List<ValidadorCadastroDePessoa> validacoes;

    public DadosDetalhamentoPessoaDto cadastrar(DadosCadastroPessoaDto dados){
        validacoes.forEach(v -> v.validar(dados));
        var pessoa = new Pessoa(dados);
        pessoaRepository.save(pessoa);
        return new DadosDetalhamentoPessoaDto(pessoa);
    }

    public List<DadosDetalhamentoPessoaDto> listar() {
        return pessoaRepository.findAll().stream().map(DadosDetalhamentoPessoaDto::new).toList();
    }
}
