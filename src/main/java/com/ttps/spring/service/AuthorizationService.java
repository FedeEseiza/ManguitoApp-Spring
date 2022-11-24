package com.ttps.spring.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttps.spring.entity.Usuario;

@Service
@Transactional
public class AuthorizationService {
	@Autowired
	UsuarioService userService;
	
	public HttpStatus verificar(Map<String, String> credenciales) {
		Usuario user = userService.findByEmail(credenciales.get("email"));
		if (user == null || !user.getPassword().equals(credenciales.get("password"))) {
			return HttpStatus.UNAUTHORIZED;
		}
		return HttpStatus.OK;
	}
}
