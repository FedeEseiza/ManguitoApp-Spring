package com.ttps.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.spring.entity.Usuario;
import com.ttps.spring.service.UsuarioService;

@RestController
//@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<?> login(@RequestBody Usuario user){
		Optional<Usuario> oUsuario = usuarioService.existeUsuario(user.getEmail(), user.getPassword());
		if (!oUsuario.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario o contraseña incorrectos");
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body("Bienvenido " + user.getEmail());
	}
}
