package ar.edu.iua;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(BackendApplication.class, args);
	}
	
	
}

/*
Modelo
  | -- > Persistencia
----------------------------
Negocio
----------------------------
Servicios WEB (REST)
  | --> servicio 1   |
  | --> servicio 2   | seguridad
  | --> servicio N   V
*/
