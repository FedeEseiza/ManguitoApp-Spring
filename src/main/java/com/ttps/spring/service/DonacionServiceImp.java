package com.ttps.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps.spring.entity.Donacion;
import com.ttps.spring.entity.Emprendimiento;
import com.ttps.spring.repository.DonacionRepository;

@Service
public class DonacionServiceImp implements DonacionService {
	
	@Autowired
	private DonacionRepository donacionRepository;
	
	@Override
	@Transactional
	public Donacion save(Donacion donacion) {
		// TODO Auto-generated method stub
		return donacionRepository.save(donacion);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Donacion> donacionesByEmprendimeinto(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Donacion> findAll() {
		// TODO Auto-generated method stub
		return donacionRepository.findAll();
	}

}
