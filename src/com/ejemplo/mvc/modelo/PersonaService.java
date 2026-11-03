package com.ejemplo.mvc.modelo;

import java.util.ArrayList;

// Responsable de la lógica de negocio.

/*
 * Valida las reglas antes de delegar al repositorio.
 * 
 * El Controlador nunca debería llamar directamente al respositorio.
 * 
 * Ejemplo: Reglas de negocio:
 * - Nombre y apellido son obligatorios y no vacíos.
 * - Edad tiene que estar entre los 0 y 120.
 * - No puede haber dos personas con el mismo apellido+nombre
 * 
 */

public class PersonaService {
	
	private final PersonaRepository repositorio;
	
	public PersonaService(PersonaRepository repositorio) {
		this.repositorio = repositorio;
	}
	
	public ArrayList<Persona> listar() {
		return repositorio.findAll();
	}
	
	public Persona buscar(Long id) {
		if (repositorio.existsById(id)) {
			return repositorio.findById(id);
		} else {
			throw new IllegalArgumentException("La persona con el id " 
					+ id + " no existe.");
		}
	}
	
	public Persona crear(String nombre, String apellido, int edad) {
		validar(nombre, apellido, edad);
		Persona persona = new Persona(nombre, apellido, edad);
		return repositorio.save(persona);
	}
	
	public Persona actualizar(Long id, String nombre, String apellido, int edad) {
		validar(nombre, apellido, edad);
		if (repositorio.existsById(id)) {
			Persona persona = repositorio.findById(id);
			persona.setNombre(nombre);
			persona.setApellido(apellido);
			persona.setEdad(edad);
			return repositorio.update(persona);
		} else {
			throw new IllegalArgumentException("La persona con el id " 
					+ id + " no existe.");
		}
	}
	
	public boolean eliminar(Long id) {
		if (repositorio.existsById(id)) {
			return repositorio.delete(id);
		} else {
			throw new IllegalArgumentException("La persona con el id " 
					+ id + " no existe.");
		}
	}
	
	
	// Centraliza las reglas de negocio.
	
	private void validar(String nombre, String apellido, int edad) {
		if (nombre == null || nombre.isEmpty()) {
			throw new IllegalArgumentException("El nombre es obligatorio.");
		}
		if (apellido == null || apellido.isEmpty()) {
			throw new IllegalArgumentException("El apellido es obligatorio.");
		}
		if (edad < 0 || edad > 120) {
			throw new IllegalArgumentException("La edad debe estar entre 0 y 120 años.");
		}
	}

}
