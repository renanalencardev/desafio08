package usa.ggti.desafio08.model.pessoa;

public record DadosDetalhamentoPessoaDto(Long id, String nome, String email, String idade) {
    public DadosDetalhamentoPessoaDto(Pessoa pessoa){
        this(pessoa.getId(), pessoa.getNome(), pessoa.getEmail(), pessoa.getIdade());
    }
}
