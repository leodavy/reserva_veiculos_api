package com.curso.reservaveiculosapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Reserva Veiculos API", version = "1.0", description = "API para realizar reservas de veiculos"))
public class ReservaveiculosapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservaveiculosapiApplication.class, args);
	}

}
