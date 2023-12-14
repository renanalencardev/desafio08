package usa.ggti.desafio08.model.pessoa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPessoaDto(String nome, String email, String idade) {
}
