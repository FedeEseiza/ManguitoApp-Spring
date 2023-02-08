package com.ttps.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	
	private static final long serialVersionUID = 4611174527592844681L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuario_rol",
		joinColumns = @JoinColumn(name="usuario_id",referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name="rol_usuario",referencedColumnName = "id")
	)
	private List<Rol> roles = new ArrayList<Rol>();
	@Column(unique=true,length=30)
	private String email;
	@Column(length=256)
	private String password;
	@Column
	private String rol;
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public Usuario() {}
	
	public Usuario(String email, String password, String rol) {
		super();
		this.email = email;
		this.password = password;
		this.rol = "publicador";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRol(Rol rol) {
		this.roles.add(rol);
	}
	
	public boolean hasEmptyFields() {
		return email.equals("") || password.equals("");
	}
	
}
