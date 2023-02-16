package com.ttps.spring.service;

import java.util.Optional;

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

	@Override
	@Transactional(readOnly = true)
	public Iterable<Categoria> findAll() {
		// TODO Auto-generated method stub
		return categoriaRepository.findAll();
	}

	@Override
	public Categoria save(Categoria categoria) {
		// TODO Auto-generated method stub
		return categoriaRepository.save(categoria);
	}

	@Override
	@Transactional(readOnly = true)
	public Categoria findCategoriaById(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findCategoriaById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Categoria> findById(Long id) {
		// TODO Auto-generated method stub
		return categoriaRepository.findById(id);
	}

	@Override
	public void deleteById(Long id) {
		categoriaRepository.deleteById(id);
	}

}
