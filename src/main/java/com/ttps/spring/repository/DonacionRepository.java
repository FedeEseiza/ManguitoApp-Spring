package com.ttps.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ttps.spring.entity.Donacion;
import com.ttps.spring.entity.Emprendimiento;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {
	
	@Query("select d from Donacion d where d.idEmprendimiento.id = ?1")
	List<Donacion> donacionesByEmprendimeinto(@Param("emprendimiento_id") Long emprendimiento_id);
}
