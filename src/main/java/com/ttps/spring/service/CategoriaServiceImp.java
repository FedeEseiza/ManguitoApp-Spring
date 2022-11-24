package com.ttps.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps.spring.entity.Categoria;
import com.ttps.spring.repository.CategoriaRepository;

@Service
public class CategoriaServiceImp implements CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Categoria findByName(String nombre) {
		// TODO Auto-generated method stub
		return categoriaRepository.findByName(nombre);
	}

}
