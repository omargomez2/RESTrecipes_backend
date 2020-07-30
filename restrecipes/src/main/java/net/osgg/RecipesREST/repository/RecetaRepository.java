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
package net.osgg.RecipesREST.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.osgg.RecipesREST.entity.Receta;

import java.util.Optional;

@Repository
public interface RecetaRepository extends JpaRepository<Receta, Long> {

	Optional <Receta> findById(Long id);
	
	void deleteById(Long id);
	
}
