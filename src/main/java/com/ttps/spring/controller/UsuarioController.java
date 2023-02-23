package com.ttps.spring.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.spring.entity.Rol;
import com.ttps.spring.entity.Usuario;
import com.ttps.spring.service.UsuarioService;
import com.ttps.spring.service.UsuarioServiceImp;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	//Creacion de un usuario
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Usuario usuario){
		Usuario user = usuarioService.findByEmail(usuario.getEmail());
//		Rol rolEmprendedor = new Rol((long) 1,"emprendedor");
		if (validarCamposUsuario(usuario)) {return ResponseEntity.status(HttpStatus.CONFLICT).body("Algunos de los campos esta vacio");}
		if (user == null) {
//			usuario.setRol(rolEmprendedor);
//			usuarioService.insertRol(rolEmprendedor.getId(), usuario.getId());
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuario));
			}
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe el usuario");
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id){
		Optional<Usuario> oUsuario = usuarioService.findById(id);
		if (!oUsuario.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(oUsuario);
	}
	
	 /*
	@PutMapping("/{email}")
	public ResponseEntity<Usuario> updatePassowrd(@PathVariable("email") String email, @RequestBody Usuario userUpdate){
		Usuario userOriginal = usuarioService.findByEmail(email);
		if (userUpdate.getPassword().equals("")) {
			return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		}
		userOriginal.setPassword(userUpdate.getPassword());
		return new ResponseEntity<Usuario>(userOriginal, HttpStatus.OK);
		
	}
	*/
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> updatePassowrd(@PathVariable("id") String id, @RequestBody Usuario userUpdate){
		Usuario userOriginal = usuarioService.findId(Long.parseLong(id));
		if (userUpdate.getPassword().equals("")) {
			return new ResponseEntity("Todos los campos son requeridos", HttpStatus.BAD_REQUEST);
		}
		if (userUpdate.getPassword().length() < 5) {
			 return new ResponseEntity("La contraseña no puede contener menos de 5 caracteres", HttpStatus.FORBIDDEN);
		 }
		userOriginal.setPassword(userUpdate.getPassword());
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(userOriginal));
		
	}
	
	public static boolean validarCamposUsuario(Usuario usuario) {
		return (usuario.getEmail() == "" || usuario.getPassword() == "");
	}
	
	
}
