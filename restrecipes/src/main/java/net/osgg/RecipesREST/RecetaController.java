																/*
 -------------------------------------------------------------------
|
| CRUDyLeaf	- A Domain Specific Language for generating Spring Boot 
|			REST resources from entity CRUD operations.
| Author: Omar S. G�mez (2020)
| File Date: Thu Jul 09 13:02:34 COT 2020
| 
 -------------------------------------------------------------------
																*/
package net.osgg.RecipesREST;


import net.osgg.RecipesREST.img.PictureService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class RecetaController {
	@Autowired
	RecetaService service;
	
	@Autowired
	PictureService picService;
	 
	@Value("${upload.path}")
	public String uploadDir;	
	
	@GetMapping("/receta")
	public ResponseEntity<List<Receta>> getAll() {
		List<Receta> list = service.getAll();
		return new ResponseEntity<List<Receta>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/receta/{id}")
	public ResponseEntity<Receta> getRecetaById(@PathVariable("id") Long id) throws RecordNotFoundException {
		Receta entity = service.findById(id);
		return new ResponseEntity<Receta>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	
    @GetMapping("/pic/{id}")
    public void getPhotoByID(@PathVariable("id") UUID id, HttpServletResponse response) throws IOException {    	
    	Path p = Paths.get(uploadDir + File.separator + id.toString()+".jpg");
    	System.out.println(p);
    	InputStream is = new FileInputStream(p.toFile());
    	response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(is, response.getOutputStream());
        is.close();
    }

	@PostMapping("/receta")
	public ResponseEntity<Receta> createReceta(@RequestParam("receta") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Receta receta = om.readValue(s, Receta[].class)[0];		
		
		if (!file.isEmpty()) {
			UUID idPic = UUID.randomUUID();
			picService.uploadPicture(file.get(0), idPic);
			receta.setFoto(idPic);		
		}
		service.createReceta(receta);
		return new ResponseEntity<Receta>(receta, new HttpHeaders(), HttpStatus.OK);
	}

	
	@PutMapping("/receta")
	public ResponseEntity<Receta> updateReceta(@RequestParam("receta") String s, @RequestParam("img") LinkedList<MultipartFile> file) throws RecordNotFoundException, JsonMappingException, JsonProcessingException{
		
		ObjectMapper om = new ObjectMapper();
		Receta receta = om.readValue(s, Receta[].class)[0];
		
	    if (!file.isEmpty()) {
	    	 picService.deletePicture(receta.getFoto());
		     UUID idPic = UUID.randomUUID();
		     picService.uploadPicture(file.get(0), idPic);
		     receta.setFoto(idPic);
	     }	
		service.updateReceta(receta);		
		return new ResponseEntity<Receta>(receta, new HttpHeaders(), HttpStatus.OK);
	}	
	

	@DeleteMapping("/receta/{id}")
	public HttpStatus deleteRecetaById(@PathVariable("id") Long id) throws RecordNotFoundException {		
		Receta receta =  service.findById(id); 	    
	    picService.deletePicture(receta.getFoto());
	    service.deleteRecetaById(id);
		return HttpStatus.OK;
	}
}				
