package co.id.implementation.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Markus Kurniawan API", version = "1.0", description = "Java Implementation Test Markus"))
@ComponentScan({"co.id.implementation.test"})
public class MarkusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarkusApplication.class, args);
	}

}
