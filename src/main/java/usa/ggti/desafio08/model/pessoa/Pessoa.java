package usa.ggti.desafio08.model.pessoa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String email;
    private String idade;

    public Pessoa(DadosCadastroPessoaDto dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.idade = dados.idade();
    }
}
