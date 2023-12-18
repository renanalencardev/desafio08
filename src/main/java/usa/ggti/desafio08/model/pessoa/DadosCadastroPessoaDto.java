package usa.ggti.desafio08.model.pessoa;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroPessoaDto(
        @NotNull(message = "O campo nome não pode ser nulo")
        String nome,
        @NotNull(message = "O campo email não pode ser nulo")
        String email,
        @NotNull(message = "O campo idade não pode ser nulo")
        String idade) {
}
