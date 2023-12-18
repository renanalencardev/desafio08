package usa.ggti.desafio08.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class SpringDocConfigurations {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                    .title("Desafio 08")
                    .description("Api Rest de cadastro e listagem de pessoas")
                    .contact(new Contact()
                            .name("Renan Alencar")
                            .email("alencar.ggti@gmail.com")
                            .url("www.linkedin.com/in/renanalencar-dev")));
    }
}
