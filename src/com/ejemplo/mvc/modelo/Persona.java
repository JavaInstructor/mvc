package com.ejemplo.mvc.modelo;

import java.util.Objects;

// Corresponde a la entidad en el modelo MVC

/*
 * ENTIDAD del dominio. Representa un registro en la tabla "persona".
 * 
 * POJO (Plain Old Java Object) sólo atributos, constructores 
 * y getters/setters.
 * 
 * No tiene lógica de negocio ni persistencia.
 * 
 */

public class Persona {
	
	private Long id;
	private String nombre;
	private String apellido;
	private int edad;
	
	public Persona() {
	
	}

	public Persona(String nombre, String apellido, int edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	// Para tener un código de identificación hash coherente.
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	// Para determinar cuándo dos objetos son iguales.	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else {
			if (obj instanceof Persona) {
				Persona persona = (Persona)obj;
				if (persona.id == this.id) {
					return true;
				}
			}
		}
		return false;
	}

	// Devolver información de valores del objeto.
	@Override
	public String toString() {
		return String.format("[%d] %s %s (%d años)"
				, id, nombre, apellido, edad);
	}

}
