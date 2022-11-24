package com.ttps.spring.service;

import com.ttps.spring.entity.Categoria;

public interface CategoriaService {
	
	public Categoria findByName(String nombre);
	
}
