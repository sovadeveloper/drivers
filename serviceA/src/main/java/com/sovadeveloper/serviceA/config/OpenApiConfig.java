package com.sovadeveloper.serviceA.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Drivers Microservices",
                description = "Two services without Gateway", version = "1.0.0",
                contact = @Contact(
                        name = "Kuzmin Maxim (sovadeveloper)",
                        email = "sovadeveloper@mail.ru"
                )
        )
)
public class OpenApiConfig {
}
