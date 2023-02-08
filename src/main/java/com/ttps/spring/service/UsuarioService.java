package com.ttps.spring.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.ttps.spring.entity.Usuario;

public interface UsuarioService {
	
	public Iterable<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Optional<Usuario> findById(Long id);
	
	public Usuario findByEmail(String email);
	
	public Usuario save(Usuario user);
	
	public void deleteById(Long id);
	
	public Optional<Usuario> existeUsuario(String email, String password);
	
	public void insertRol(Long usuario_id, Long rol_usuario);
	
	public ResponseEntity<Usuario> crear(Usuario userNuevo);
	
	public Usuario findId(Long id);
}
