package app.backend.library.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@OpenAPIDefinition
public class OpenApiConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8080");

        Contact myContact = new Contact();
        myContact.setName("Babek Asadli");
        myContact.setEmail("babekasadli@gmail.com");

        Info information = new Info()
                .title("Library API")
                .version("1.0")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}

