package usa.ggti.desafio08.domain.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoCadastroDePessoa {
    @Autowired
    private PessoaRepository pessoaRepository;

    public DadosDetalhamentoPessoaDto cadastrar(DadosCadastroPessoaDto dados){
        var pessoa = new Pessoa(dados);
        pessoaRepository.save(pessoa);
        return new DadosDetalhamentoPessoaDto(pessoa);
    }

    public List<DadosDetalhamentoPessoaDto> listar() {
        return pessoaRepository.findAll().stream().map(DadosDetalhamentoPessoaDto::new).toList();
    }
}
