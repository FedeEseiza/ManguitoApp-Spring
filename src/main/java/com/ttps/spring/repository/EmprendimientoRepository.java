package com.ttps.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ttps.spring.entity.Emprendimiento;

public interface EmprendimientoRepository extends JpaRepository<Emprendimiento, Long> {
	@Query("select e from Emprendimiento e where lower(e.nombre)=lower(?1)")
	Emprendimiento findByNombre(@Param("nombre") String nombre);
	
	@Query("select e from Emprendimiento e where e.usuario.id=?1")
	Emprendimiento findUserById(@Param("id") Long id);
	
	@Query("select e from Emprendimiento e")
	List<Emprendimiento> findAllEmprendimientos();
}
