package usa.ggti.desafio08.model.pessoa.validacoes;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidarEmailExistente.class)
public @interface EmailExist {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
