package com.ttps.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ttps.spring.entity.Emprendimiento;

public interface EmprendimientoService {
	
	public Iterable<Emprendimiento> findAll();
	
	public Page<Emprendimiento> findAll(Pageable pageable);
	
	public Optional<Emprendimiento> findById(Long id);
	
	public Emprendimiento save(Emprendimiento emprendimiento);
	
	public Emprendimiento findByNombre(String nombre);
	
	public Emprendimiento findUserById(Long id);
	
	public List<Emprendimiento> findAllEmprendimientos();
	
	public Emprendimiento findEmpById(Long id);
}
