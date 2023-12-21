package usa.ggti.desafio08.model.pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import usa.ggti.desafio08.model.pessoa.dtos.DadosAtualizacaoPessoaDto;
import usa.ggti.desafio08.model.pessoa.dtos.DadosCadastroPessoaDto;

@Entity(name = "Pessoa")
@Table(name = "pessoas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String idade;
    private boolean ativo;

    public Pessoa(DadosCadastroPessoaDto dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.idade = dados.idade();
    }

    public void inativar() {
        this.ativo = false;
    }

    public void atualizarDados(DadosAtualizacaoPessoaDto dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.email() != null){
            this.email = dados.email();
        }
        if(dados.idade() != null){
            this.idade = dados.idade();
        }
    }
}
