package com.ttps.spring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ttps.spring.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
		@Query("select u from Usuario u where lower(u.email)=lower(?1)")
		Usuario findByEmail(@Param ("email") String email);
		
		@Query("select u from Usuario u where u.email = ?1 and u.password = ?2")
		Optional<Usuario> existeUsuario(@Param("email") String email, @Param("password") String password);
		
		@Query("select u from Usuario u where u.id=?1")
		Usuario findId(@Param ("id") Long id);
		
}
