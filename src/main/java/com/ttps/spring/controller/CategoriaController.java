package com.ttps.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.spring.entity.Categoria;
import com.ttps.spring.service.CategoriaService;

@CrossOrigin(origins = "http://localhost:4200/api/", maxAge = 3600)
@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService catService;
	
//	@CrossOrigin(origins = "http://localhost:4200/api/categorias/", maxAge = 3600)
	@GetMapping("/categorias")
	public ResponseEntity<List<Categoria>> getAll(){
		List<Categoria> categorias = StreamSupport
				.stream(catService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return new ResponseEntity<List<Categoria>>(categorias,HttpStatus.OK); 
	}
	
	@PostMapping("/nueva-categoria")
	public ResponseEntity<?> create(@RequestBody Categoria categoriaNueva){
		if (categoriaNueva == null) {
			return ResponseEntity.notFound().build();
		}
		Categoria cat = catService.findByName(categoriaNueva.getNombre());
		if (cat != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(catService.save(categoriaNueva));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Categoria nuevaCategoria){
		Categoria catOriginal = catService.findCategoriaById(Long.parseLong(id));
		if (catOriginal == null) {
			return ResponseEntity.notFound().build();
		}
		if (catService.findByName(nuevaCategoria.getNombre()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		catOriginal.setNombre(nuevaCategoria.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(catService.save(catOriginal));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id){
		
		if(!catService.findById(Long.parseLong(id)).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		catService.deleteById(Long.parseLong(id));
		return ResponseEntity.ok().build();
	}
}
