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
package net.osgg.RecipesREST;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecetaService {

	@Autowired
	RecetaRepository repo;

	public List<Receta> getAll(){
		List<Receta> recetaList = repo.findAll();
		if(recetaList.size() > 0) {
			return recetaList;
		} else {
			return new ArrayList<Receta>();
		}
	}
     		
	public Receta findById(Long id) throws RecordNotFoundException{
		Optional<Receta> receta = repo.findById(id);
		if(receta.isPresent()) {
			return receta.get();
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public Receta createReceta(Receta receta){
		return repo.save(receta);
	}

	public Receta updateReceta(Receta receta) throws RecordNotFoundException {
		Optional<Receta> recetaTemp = repo.findById(receta.getId());
	
		if(recetaTemp.isPresent()){
			return repo.save(receta);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}

	public void deleteRecetaById(Long id) throws RecordNotFoundException{
		Optional<Receta> receta = repo.findById(id);
		if(receta.isPresent()) {
		repo.deleteById(id);
		} else {
			throw new RecordNotFoundException("Record does not exist for the given Id");
		}
	}		

}
