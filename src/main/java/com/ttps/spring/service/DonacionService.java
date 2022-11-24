package com.ttps.spring.service;

import java.util.List;

import com.ttps.spring.entity.Donacion;
import com.ttps.spring.entity.Emprendimiento;

public interface DonacionService {
	
	public Donacion save(Donacion donacion);
	
	public List<Donacion> donacionesByEmprendimeinto(Long id);
	
	public Iterable<Donacion> findAll();
}
