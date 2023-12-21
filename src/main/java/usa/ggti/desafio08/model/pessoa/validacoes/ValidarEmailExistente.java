package usa.ggti.desafio08.model.pessoa.validacoes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import usa.ggti.desafio08.model.pessoa.PessoaRepository;

@Component
public class ValidarEmailExistente implements ConstraintValidator<EmailExist, Object> {
    @Autowired
    private PessoaRepository pessoaRepository;
    @Override
    public void initialize(EmailExist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
    @Override
    public boolean isValid(Object input, ConstraintValidatorContext constraintValidatorContext) {
//        if(input == null){
//            return false;
//        }
        if(pessoaRepository.existsByEmail((String) input)){
            return false;
        }
        return true;
    }
}
