package com.ttps.spring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps.spring.entity.Usuario;
import com.ttps.spring.repository.UsuarioRepository;

@Service
public class UsuarioServiceImp implements UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public Iterable<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return usuarioRepository.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findById(id);
	}

	@Override
	@Transactional
	public Usuario save(Usuario usuario) {
		// TODO Auto-generated method stub
		return usuarioRepository.save(usuario);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		usuarioRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Usuario findByEmail(String email) {
		// TODO Auto-generated method stub
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Optional<Usuario> existeUsuario(String email, String password) {
		// TODO Auto-generated method stub
		return usuarioRepository.existeUsuario(email, password);
	}

	@Override
	public void insertRol(Long usuario_id, Long rol_usuario) {
		// TODO Auto-generated method stub
		
	}
	
	public ResponseEntity crear(Usuario userNuevo) {
		Usuario user = usuarioRepository.findByEmail(userNuevo.getEmail());
		if (user != null) {
			 System.out.println("Ya existe un usuario con mail " + userNuevo.getEmail());
			return new ResponseEntity("Ya existe un usuario con mail " + userNuevo.getEmail(), HttpStatus.CONFLICT); 
		}
		usuarioRepository.save(userNuevo);
		return new ResponseEntity(HttpStatus.OK);
	}

	@Override
	public Usuario findId(Long id) {
		// TODO Auto-generated method stub
		return usuarioRepository.findId(id);
	}

}
