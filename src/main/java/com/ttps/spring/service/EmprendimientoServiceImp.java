package com.ttps.spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps.spring.entity.Emprendimiento;
import com.ttps.spring.repository.EmprendimientoRepository;

@Service
public class EmprendimientoServiceImp implements EmprendimientoService {
	
	@Autowired
	private EmprendimientoRepository empRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Emprendimiento> findAll() {
		// TODO Auto-generated method stub
		return empRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Emprendimiento> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return empRepository.findAll(pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Emprendimiento> findById(Long id) {
		// TODO Auto-generated method stub
		return empRepository.findById(id);
	}

	@Override
	public Emprendimiento save(Emprendimiento emprendimiento) {
		// TODO Auto-generated method stub
		return empRepository.save(emprendimiento);
	}

	@Override
	public Emprendimiento findByNombre(String nombre) {
		return empRepository.findByNombre(nombre);
	}

	@Override
	public Emprendimiento findUserById(Long id) {
		// TODO Auto-generated method stub
		return empRepository.findUserById(id);
	}

	@Override
	public List<Emprendimiento> findAllEmprendimientos() {
		// TODO Auto-generated method stub
		return empRepository.findAllEmprendimientos();
	}

	@Override
	public Emprendimiento findEmpById(Long id) {
		// TODO Auto-generated method stub
		return empRepository.findEmpById(id);
	}
	
}
