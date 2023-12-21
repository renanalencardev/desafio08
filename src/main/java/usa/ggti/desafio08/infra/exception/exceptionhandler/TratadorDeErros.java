package usa.ggti.desafio08.infra.exception.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import usa.ggti.desafio08.infra.exception.ValidacaoCadastroPessoaException;
import usa.ggti.desafio08.infra.exception.ValidacaoListagemPessoaException;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ValidacaoCadastroPessoaException.class)
    public ResponseEntity tratarErroCadastroDePessoa(ValidacaoCadastroPessoaException exception){
        var erros = exception.getMessage();
        var status = HttpStatus.BAD_REQUEST.toString();
        var dto = new DadosErrosValidacaoPessoa(status, erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DadosErrosValidacaoPessoa(status, erros));
    }
    @ExceptionHandler(ValidacaoListagemPessoaException.class)
    public ResponseEntity tratarErroListagemDePessoa(ValidacaoListagemPessoaException exception){
        var erros = exception.getMessage();
        var status = HttpStatus.NOT_FOUND.toString();
        var dto = new DadosErrosValidacaoPessoa(status, erros);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new DadosErrosValidacaoPessoa(status, erros));
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErroAtributoNull(MethodArgumentNotValidException exception){
        var erros = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErrosArgumentoInvalido::new).toList());
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity tratarCorpoDaRequisicaoAusente(HttpMessageNotReadableException exception) {
        var erro = "Corpo da requisição ausente ou inválido.";
        var status = HttpStatus.BAD_REQUEST.toString();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    private record DadosErrosValidacaoPessoa(String status, String erros){

        public DadosErrosValidacaoPessoa(String status, String erros){
            this.erros = erros;
            this.status = status;
        }
    }
    private record DadosErrosArgumentoInvalido(String field, String message){
        public DadosErrosArgumentoInvalido(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
