package com.ttps.spring.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.spring.entity.Donacion;
import com.ttps.spring.entity.Emprendimiento;
import com.ttps.spring.service.DonacionService;
import com.ttps.spring.service.EmprendimientoService;

@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {
	
	@Autowired
	private DonacionService donacionService;
	@Autowired
	private EmprendimientoService empService;
	
	@PostMapping("/{nombre}")
	public ResponseEntity<?> create(@PathVariable String nombre ,@RequestBody Donacion donacion){
		Emprendimiento empAux = empService.findByNombre(nombre);
		if (empAux == null) {
			return ResponseEntity.notFound().build();
		}
		if (donacion.getCantidadManguitos() <= 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La cantidad de manguitos debe ser mayor a 0");
		}
		empAux.setDonaciones(donacion);
		empService.save(empAux);
		donacion.setIdEmprendimiento(empAux);
		return ResponseEntity.ok().body(donacionService.save(donacion));
	}
	
	@GetMapping("/{nombre}")
	public List<Donacion> getAll(@PathVariable String nombre){
		Emprendimiento empAux = empService.findByNombre(nombre);
		if (empAux == null) {
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No se encontro el Emprendimiento");
		}
		List<Donacion> donaciones = StreamSupport
				.stream(donacionService.findAll().spliterator(), false)
				.collect(Collectors.toList());
		return donaciones;
	}
}
