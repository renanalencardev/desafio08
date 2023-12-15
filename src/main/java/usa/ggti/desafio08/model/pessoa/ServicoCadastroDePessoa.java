package usa.ggti.desafio08.model.pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.ggti.desafio08.model.pessoa.validacoes.ValidadorCadastroDePessoa;
import usa.ggti.desafio08.util.PadronizarNome;

import java.util.List;

@Service
public class ServicoCadastroDePessoa {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private List<ValidadorCadastroDePessoa> validacoes;

    public DadosDetalhamentoPessoaDto cadastrar(DadosCadastroPessoaDto dados){
        validacoes.forEach(v -> v.validar(dados));

        PadronizarNome padronizarNome = new PadronizarNome();
        String nome = padronizarNome.primeiraLetraMaiuscula(dados.nome());

        var pessoa = new Pessoa(new DadosCadastroPessoaDto(nome, dados.email(), dados.idade()));
        pessoaRepository.save(pessoa);
        return new DadosDetalhamentoPessoaDto(pessoa);
    }

    public List<DadosDetalhamentoPessoaDto> listar() {
        return pessoaRepository.findAll().stream().map(DadosDetalhamentoPessoaDto::new).toList();
    }
}
