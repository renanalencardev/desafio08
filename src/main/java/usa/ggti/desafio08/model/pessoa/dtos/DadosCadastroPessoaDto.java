package usa.ggti.desafio08.model.pessoa.dtos;

import jakarta.validation.constraints.*;
import usa.ggti.desafio08.model.pessoa.validacoes.EmailExist;

public record DadosCadastroPessoaDto(
        @NotNull(message = "O campo nome não pode ser nulo")
        @NotBlank(message = "O campo nome não pode ser vazio.")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "Caracteres inválidos: Digite apenas letras no campo nome.")
        String nome,
        @EmailExist(message = "Email existente")
        @NotNull(message = "O campo email não pode ser nulo")
        @NotBlank(message = "O campo email não pode ser vazio.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Formato de email inválido")
        String email,
        @NotNull(message = "O campo idade não pode ser nulo")
        @NotBlank(message = "O campo idade não pode ser vazio.")
        @Min(value = 17, message = "A idade deve ser maior ou igual a 17")
        @Max(value = 65, message = "A idade deve ser menor ou igual a 65")
        String idade) {
}
