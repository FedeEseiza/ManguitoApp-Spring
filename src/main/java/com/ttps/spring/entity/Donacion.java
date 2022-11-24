package com.ttps.spring.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
@Table(name="donacionEmprendimiento")
public class Donacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="emprendimiento_id")
	@JsonIgnore
	private Emprendimiento idEmprendimiento;
	@Column
	private int cantidadManguitos;
	@Column(length = 20)
	private String contacto;
	@Column(length = 50)
	private String mensaje;
	@Column
	private Double precioHistoricoManguito;
	
	public Donacion() {
		super();
	}

	public Donacion(Emprendimiento idEmprendimiento, int cantidadManguitos, String contacto, String mensaje,
			Double precioHistoricoManguito) {
		super();
		this.idEmprendimiento = idEmprendimiento;
		this.cantidadManguitos = cantidadManguitos;
		this.contacto = contacto;
		this.mensaje = mensaje;
		this.precioHistoricoManguito = precioHistoricoManguito;
	}



	public Emprendimiento getIdEmprendimiento() {
		return idEmprendimiento;
	}

	public void setIdEmprendimiento(Emprendimiento idEmprendimiento) {
		this.idEmprendimiento = idEmprendimiento;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getCantidadManguitos() {
		return cantidadManguitos;
	}
	public void setCantidadManguitos(int cantidadManguitos) {
		this.cantidadManguitos = cantidadManguitos;
	}
	public String getContacto() {
		return contacto;
	}
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
