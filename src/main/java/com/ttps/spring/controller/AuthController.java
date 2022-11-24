package com.ttps.spring.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.spring.entity.Usuario;
import com.ttps.spring.service.AuthorizationService;
import com.ttps.spring.service.TokenService;
import com.ttps.spring.service.UsuarioService;

@CrossOrigin
@RestController
public class AuthController {
	@Autowired
	private UsuarioService userService;
	@Autowired
	private AuthorizationService authService;
	@Autowired
	private TokenService tokenService;
	private final int EXPIRATION_IN_SEC= 3600;
	
	@PostMapping("/api/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credenciales){
		if (credenciales.get("email") == "" || credenciales.get("password") == "") {
			return new ResponseEntity("El email y la contraseña no pueden estar vacios", HttpStatus.BAD_REQUEST);
		}
		HttpStatus response = authService.verificar(credenciales);
		if (response != HttpStatus.OK) {
			return new ResponseEntity("Email o contraseña incorrecta", response);
		}
		String token = tokenService.generarToken(credenciales.get("email"), EXPIRATION_IN_SEC);
		Map<String, String> data = new HashMap<String, String>();
		Usuario user = userService.findByEmail(credenciales.get("email"));
		data.put("user_id", Long.toString(user.getId()));
		data.put("email", user.getEmail());
		data.put("token", token);
		return new ResponseEntity<Map<String, String>>(data, HttpStatus.OK);
	}	
	
	@PostMapping("/api/registrarse")
	public ResponseEntity<Usuario> registrarse(@RequestBody Usuario userNuevo){
		 if (userNuevo.hasEmptyFields()) {
			 return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		 }
		 
		 ResponseEntity codigoRta = userService.crear(userNuevo);
		 if (codigoRta.getStatusCode() != HttpStatus.OK) {
			 return codigoRta;
		 }
		 return new ResponseEntity<Usuario>(userNuevo, HttpStatus.CREATED);
	}
}
