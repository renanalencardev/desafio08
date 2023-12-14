package usa.ggti.desafio08.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import usa.ggti.desafio08.domain.pessoa.validacoes.ValidacaoPessoaException;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ValidacaoPessoaException.class)
    public ResponseEntity tratarErroCadastroDePessoa(ValidacaoPessoaException exception){
        var erros = exception.getMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DadosErrosValidacao(erros));
    }
    private record DadosErrosValidacao(String message){
        public DadosErrosValidacao(String message){
            this.message = message;
        }
    }
}
