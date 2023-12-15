package usa.ggti.desafio08.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import usa.ggti.desafio08.model.pessoa.validacoes.ValidacaoPessoaException;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ValidacaoPessoaException.class)
    public ResponseEntity tratarErroCadastroDePessoa(ValidacaoPessoaException exception){
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroAtributoNull(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosArgumentoInvalido::new).toList());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> tratarCorpoDaRequisicaoAusente(HttpMessageNotReadableException exception) {
        var erro = "Corpo da requisição ausente ou inválido.";
        var status = HttpStatus.BAD_REQUEST.toString();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DadosErrosValidacaoPessoa(status, erro).toString());
    }

            this.message = message;
    private record DadosErrosArgumentoInvalido(String field, String message){
        public DadosErrosArgumentoInvalido(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
