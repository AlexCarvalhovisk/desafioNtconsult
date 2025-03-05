package br.com.alexcarvalho.desafio.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("API de Pautas")
                    .description("API para gerenciamento de pautas e votações")
                    .version("1.0.0")
                        .contact(new Contact()
                            .name("Alex Carvalho")
                            .email("alex.carvalhovisk@gmail.com")
                            .url("https://github.com/AlexCarvalhovisk"))
                                .license(new License()
                                    .name("Apache 2.0")
                                    .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                )
                .externalDocs(new ExternalDocumentation()
                    .description("Documentação Completa da API de Pautas")
                    .url("https://github.com/AlexCarvalhovisk/desafioNtconsult"));
    }
}
