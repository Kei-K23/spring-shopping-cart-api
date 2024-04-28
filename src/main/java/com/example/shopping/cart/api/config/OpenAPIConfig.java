package com.example.shopping.cart.api.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("https://spring-shopping-cart-api.onrender.com");
        server.setDescription("Production");

        Contact myContact = new Contact();
        myContact.setName("Arkar Min");
        myContact.setEmail("arkar1712luffy@gmail.com");

        Info information = new Info()
                .title("Shopping Cart with products management API")
                .version("1.0")
                .description("This API exposes endpoints to manage products and shopping carts.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
