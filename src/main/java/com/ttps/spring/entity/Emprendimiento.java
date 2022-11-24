package com.ttps.spring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="emprendimiento")
public class Emprendimiento implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne(fetch = FetchType.EAGER)
	private Usuario usuario;
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="emprendimiento_categoria",
		joinColumns = @JoinColumn(name="emprendimiento_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name="categoria_id", referencedColumnName = "id")
	)
	private Set<Categoria> categorias = new HashSet<Categoria>();
	@OneToMany(mappedBy = "idEmprendimiento")
	private List<Donacion> donaciones = new ArrayList<Donacion>();
	@Column(length = 20, unique = true)
	private String nombre;
	@Column(length = 100)
	private String descripcion;
	@Column
	private Boolean mostrarManguitosRecibidos;
	@Column
	private Boolean mostrarTopDonadores;
	@Column
	private Double precioPorManguito;
	
	public Emprendimiento() {
		super();
	}
	public Emprendimiento(String nombre, String descripcion, Boolean mostrarManguitosRecibidos,
			Boolean mostrarTopDonadores, Double precioPorManguito) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.mostrarManguitosRecibidos = mostrarManguitosRecibidos;
		this.mostrarTopDonadores = mostrarTopDonadores;
		this.precioPorManguito = precioPorManguito;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getMostrarManguitosRecibidos() {
		return mostrarManguitosRecibidos;
	}
	public void setMostrarManguitosRecibidos(Boolean mostrarManguitosRecibidos) {
		this.mostrarManguitosRecibidos = mostrarManguitosRecibidos;
	}
	public Boolean getMostrarTopDonadores() {
		return mostrarTopDonadores;
	}
	public void setMostrarTopDonadores(Boolean mostrarTopDonadores) {
		this.mostrarTopDonadores = mostrarTopDonadores;
	}
	public Double getPrecioPorManguito() {
		return precioPorManguito;
	}
	public void setPrecioPorManguito(Double precioPorManguito) {
		this.precioPorManguito = precioPorManguito;
	}
	
	public void setCategorias(Set<Categoria> categorias) {
		this.categorias.addAll(categorias);
	}
	
	public Set<Categoria> getCategorias() {
		return this.categorias;
	}
	public List<Donacion> getDonaciones() {
		return donaciones;
	}
	public void setDonaciones(Donacion donacion) {
		this.donaciones.add(donacion);
	}
	
	
}
