																/*
 -------------------------------------------------------------------
|
| CRUDyLeaf	- A Domain Specific Language for generating Spring Boot 
|			REST resources from entity CRUD operations.
| Author: Omar S. Gómez (2020)
| File Date: Sun Jul 12 09:54:32 COT 2020
| 
 -------------------------------------------------------------------
																*/
package net.osgg.RecipesREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;
import javax.annotation.PostConstruct;					

@SpringBootApplication
public class RecipesRESTApplication {
	public static void main(String[] args) {
		SpringApplication.run(RecipesRESTApplication.class, args);
		System.out.println("Active resources for Receta entity");
		System.out.println("GET");
		System.out.println("/api/v1/receta");
		System.out.println("/api/v1/receta/{id}");
		System.out.println("POST");
		System.out.println("/api/v1/receta");
		System.out.println("PUT");
		System.out.println("/api/v1/receta");
		System.out.println("DELETE");
		System.out.println("/api/v1/receta/{id}");
	}
	
	@PostConstruct
	public void init(){
		TimeZone.setDefault(TimeZone.getTimeZone("America/Guayaquil"));
	}						
}
