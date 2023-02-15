package com.ttps.spring.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.spring.entity.Categoria;
import com.ttps.spring.entity.Emprendimiento;
import com.ttps.spring.service.CategoriaService;
import com.ttps.spring.service.EmprendimientoService;
import com.ttps.spring.service.UsuarioService;

@RestController
@RequestMapping("/api/emprendimiento")
public class EmprendimientoController {
	
	@Autowired
	private EmprendimientoService empService;
//	@Autowired
//	private CategoriaService categoriaService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Emprendimiento emp){
		Emprendimiento empAux = empService.findUserById(emp.getUsuario().getId());
		if (empAux == null) {return ResponseEntity.status(HttpStatus.CREATED).body(empService.save(emp));}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya tiene un emprendimiento");
	}
	/*
	@GetMapping("/{nombre}")
	public ResponseEntity<?> read(@PathVariable String nombre){
		Emprendimiento emp = empService.findByNombre(nombre);
		if (emp != null) {
			return ResponseEntity.ok(emp);
		}
		return ResponseEntity.notFound().build();
	}
	*/
	@GetMapping("/{id}")
	public ResponseEntity<?> obtenerEmprendimientoById(@PathVariable String id){
		Emprendimiento emp = empService.findEmpById(Long.parseLong(id));
		if (emp != null) {
			return ResponseEntity.ok(emp);
		}
		return null;
	}
	
	@GetMapping("/emprendimientos")
	public ResponseEntity<List<Emprendimiento>> getAll(){
		List<Emprendimiento> emprendimientos = StreamSupport
				.stream(empService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return new ResponseEntity<List<Emprendimiento>>(emprendimientos,HttpStatus.OK);
	}
	
	@PutMapping("/{nombre}")
	public ResponseEntity<?> updateEmprendimiento(@PathVariable("nombre") String nombre, @RequestBody Emprendimiento emp){
		Emprendimiento newEmp = empService.findByNombre(nombre);
		if (newEmp == null) {
			return ResponseEntity.notFound().build();
		}
		
		newEmp.setDescripcion(emp.getDescripcion());
		newEmp.setMostrarManguitosRecibidos(emp.getMostrarManguitosRecibidos());
		newEmp.setMostrarTopDonadores(emp.getMostrarTopDonadores());
		newEmp.setPrecioPorManguito(emp.getPrecioPorManguito());
		
		return ResponseEntity.status(HttpStatus.OK).body(empService.save(newEmp));
	}
	
	public static boolean validarCampos(Emprendimiento emp) {
		return ((emp.getNombre() == "") || (emp.getUsuario() == null));
	}
	
	
	
}
