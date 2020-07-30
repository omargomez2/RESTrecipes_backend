																/*
 -------------------------------------------------------------------
|
| CRUDyLeaf	- A Domain Specific Language for generating Spring Boot 
|			REST resources from entity CRUD operations.
| Author: Omar S. G�mez (2020)
| File Date: Sun Jul 12 09:54:32 COT 2020
| 
 -------------------------------------------------------------------
																*/
package net.osgg.RecipesREST.entity;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;	
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.UUID;
import org.hibernate.annotations.Type;
	
@Entity
@Table(name = "receta_table")
public class Receta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	@Type(type = "uuid-char")
	private UUID foto;
	private String preparacion;
	private String dificultad;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public UUID getFoto() {
		return foto;
	}
	
	public void setFoto(UUID foto) {
		this.foto = foto;
	}
	
	public String getPreparacion() {
		return preparacion;
	}
	
	public void setPreparacion(String preparacion) {
		this.preparacion = preparacion;
	}
	
	public String getDificultad() {
		return dificultad;
	}
	
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	
}
