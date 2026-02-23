package com.example.departamentservices;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@OpenAPIDefinition(
		info = @Info(
				title = "Departament API",
				version = "1.0",
				description = "Documentation Departament API v1.0",
				contact = @Contact(
						name = "Rafael",
						email = "luisdcm_18hotmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "SpringShop Wiki Documentation",
				url = "https://springshop.wiki.github.org/docs"
		)
)

@SpringBootApplication
@EnableFeignClients
public class DepartamentServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartamentServicesApplication.class, args);
	}

}
