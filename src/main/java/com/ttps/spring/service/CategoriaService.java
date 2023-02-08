package com.ttps.spring.service;

import com.ttps.spring.entity.Categoria;

public interface CategoriaService {
	
	public Categoria findByName(String nombre);
	
	public Iterable<Categoria> findAll();
	
	public Categoria save(Categoria categoria);
	
	public Categoria findCategoriaById(Long id);
	
}
