package com.ejemplo.mvc.modelo;

import java.util.ArrayList;
import java.util.HashMap;

// Tiene la responsabilidad del acceso a datos (DAO)

/*
 * REPOSITORIO / DAO (Data Access Object)
 * 
 * Es la que sabe cómo se almacenan los datos.
 * 
 * Vamos a usar un mapa para guardar los datos en memoria.
 * 
 */

public class PersonaRepository {
	
	// Esto funciona como "una base de datos" en memoria.
	private final HashMap<Long, Persona> personas 
		= new HashMap<Long, Persona>();
	
	private Long secuencia = 1L;

	public ArrayList<Persona> findAll() {
		return new ArrayList<Persona>(personas.values());
	}
	
	public Persona findById(Long id) {
		return personas.get(id);
	}
	
	public Persona save(Persona persona) {
		if (persona.getId() == null) {
			persona.setId(secuencia++);
		}
		personas.put(persona.getId(), persona);
		return persona;
	}
	
	public Persona update(Persona persona) {
		if (persona.getId() == null || 
				!personas.containsKey(persona.getId())) {
			throw new IllegalArgumentException("La persona con el id " 
				+ persona.getId() + " no existe.");
		}
		personas.put(persona.getId(), persona);
		return persona;
	}
	
	public boolean delete(Long id) {
		if (personas.remove(id) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	// Agregamos un método extra:
	
	public boolean existsById(Long id) {
		return personas.containsKey(id);
	}
	
}
