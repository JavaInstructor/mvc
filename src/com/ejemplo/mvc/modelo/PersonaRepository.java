package com.ejemplo.mvc.modelo;

import java.util.ArrayList;

public interface PersonaRepository {

	public ArrayList<Persona> findAll();
	public Persona findById(Long id);
	public Persona save(Persona persona);
	public Persona update(Persona persona);
	public boolean delete(Long id);
	public boolean existsById(Long id);
	
}
