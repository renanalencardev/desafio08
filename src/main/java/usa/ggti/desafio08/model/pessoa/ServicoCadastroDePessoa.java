package usa.ggti.desafio08.model.pessoa;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import usa.ggti.desafio08.infra.exception.ValidacaoListagemPessoaException;
import usa.ggti.desafio08.model.pessoa.dtos.DadosAtualizacaoPessoaDto;
import usa.ggti.desafio08.model.pessoa.dtos.DadosCadastroPessoaDto;
import usa.ggti.desafio08.model.pessoa.dtos.DadosDetalhamentoPessoaDto;
import usa.ggti.desafio08.model.pessoa.validacoes.ValidadorCadastroDePessoa;
import usa.ggti.desafio08.util.PadronizarNome;

import java.util.List;

@Service
public class ServicoCadastroDePessoa {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private List<ValidadorCadastroDePessoa> validacoes;
    @Autowired
    private PadronizarNome padronizarNome;
    @Transactional
    public DadosDetalhamentoPessoaDto cadastrar(DadosCadastroPessoaDto dados){
        //validacoes.forEach(validador -> validador.validar(dados));
        String nome = padronizarNome.primeiraLetraMaiuscula(dados.nome());
        var pessoa = new Pessoa(new DadosCadastroPessoaDto(nome, dados.email(), dados.idade()));
        pessoaRepository.save(pessoa);
        return new DadosDetalhamentoPessoaDto(pessoa);
    }

    public List<DadosDetalhamentoPessoaDto> listar() {
        return pessoaRepository.findAllByAtivoTrue().stream().map(DadosDetalhamentoPessoaDto::new).toList();
    }

    public DadosDetalhamentoPessoaDto buscarPorId(Long id) {
        if(!pessoaRepository.existsById(id)){
            throw new ValidacaoListagemPessoaException("Usuário não encontrado na base de dados");
        }
        var pessoa = pessoaRepository.getReferenceById(id);
        return new DadosDetalhamentoPessoaDto(pessoa);
    }

    @Transactional
    public void excluirPorId(Long id) {
        if(!pessoaRepository.existsById(id)){
            throw new ValidacaoListagemPessoaException("Usuário não encontrado na base de dados");
        }
        var pessoa = pessoaRepository.getReferenceById(id);
        pessoa.inativar();
    }
    @Transactional
    public DadosDetalhamentoPessoaDto atualizar(DadosAtualizacaoPessoaDto dados, Long id) {
        if(!pessoaRepository.existsById(id)){
            throw new ValidacaoListagemPessoaException("Usuário não encontrado na base de dados");
        }
        var nome = dados.nome();
        if(nome != null){
            nome = padronizarNome.primeiraLetraMaiuscula(dados.nome());
        }
        var dadosCorrigidos = new DadosAtualizacaoPessoaDto(nome, dados.email(), dados.idade());
        var pessoa = pessoaRepository.getReferenceById(id);
        pessoa.atualizarDados(dadosCorrigidos);
        return new DadosDetalhamentoPessoaDto(pessoa);
    }
}
