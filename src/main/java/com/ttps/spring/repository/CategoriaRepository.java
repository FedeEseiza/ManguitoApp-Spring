package com.ttps.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ttps.spring.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	@Query("select c from Categoria c where lower(c.nombre) = lower(?1)")
	Categoria findByName(@Param("nombre")String nombre);
	
	@Query("select c from Categoria c where c.id = ?1")
	Categoria findCategoriaById(@Param("id") Long id);
}
