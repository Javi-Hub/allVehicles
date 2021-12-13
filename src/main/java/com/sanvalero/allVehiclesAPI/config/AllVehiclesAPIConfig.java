package com.sanvalero.allVehiclesAPI.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Creado por @author: Javier
 * el 28/03/2021
 */
@Configuration
public class AllVehiclesAPIConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Information all vehicles")
                        .description("Actividad de Aprendizaje Acceso a Datos 2ª Evaluación")
                        .contact(new Contact()
                                .name("Javier")
                                .email("javi@mail.com")
                                .url("https://github.com/Javi-Hub/allVehicles.git"))
                        .version("1.0"));
    }

}
