package com.ejemplo.mvc.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonaRepositoryFile2 implements PersonaRepository {
	
	private static final String ARCHIVO = "personas.txt";
	private static final String SEP = ";";

	public ArrayList<Persona> findAll() {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		File file = new File(ARCHIVO);
		
		if (file.exists()) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(file);
				while (scanner.hasNext()) {
					personas.add(parserLine(scanner.nextLine()));
				}
			} catch (FileNotFoundException error) {
				System.out.println("Error: " + error.getMessage());
			} finally {
				scanner.close();
			}
		} else {
			System.out.println("El archivo '" + ARCHIVO + "' no existe.");
		}
		return personas;
	}
	
	public Persona findById(Long id) {
		ArrayList<Persona> personas = findAll();
		
		for (Persona persona : personas) {
			if (persona.getId() == id) {
				return persona;
			}
		}
		return null;
	}
	
	public Persona save(Persona persona) {
		ArrayList<Persona> personas = findAll();
		
		if (personas.size() > 0) {
			Long ultimoId = personas.get(personas.size() - 1).getId();
			persona.setId(ultimoId + 1);
		} else {
			persona.setId(1L);
		}
		personas.add(persona);
		parserList(personas);
		return persona;
	}
	
	
	public Persona update(Persona persona) {
		ArrayList<Persona> personas = findAll();
		
		for (Persona p : personas) {
			if (p.getId() == persona.getId()) {
				p.setNombre(persona.getNombre());
				p.setApellido(persona.getApellido());
				p.setEdad(persona.getEdad());
			}
		}
		parserList(personas);
		return persona;
	}
	
	public boolean delete(Long id) {
		ArrayList<Persona> personas = findAll();
		
		for (Persona p : personas) {
			if (p.getId() == id) {
				personas.remove(p);
			}
		}
		parserList(personas);
		return true;
	}
	
	public boolean existsById(Long id) {
		ArrayList<Persona> personas = findAll();
		
		for (Persona p : personas) {
			if (p.getId() == id) {
				return true;
			}
		}
		return false;
	}
	
	private Persona parserLine(String linea) {
		Persona persona = new Persona();
		String[] elementos = linea.split(SEP);
		
		persona.setId(Long.parseLong(elementos[0]));
		persona.setNombre(elementos[1]);
		persona.setApellido(elementos[2]);
		persona.setEdad(Integer.parseInt(elementos[3]));
		
		return persona;
	}
	
	private void parserList(ArrayList<Persona> personas) {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new FileWriter(ARCHIVO, false));
			for (Persona persona : personas) {
				String linea = persona.getId() 
						+ SEP + persona.getNombre()
						+ SEP + persona.getApellido()
						+ SEP + persona.getEdad();
				pw.println(linea);
			}
		} catch (IOException error) {
			System.out.println("Error: " + error.getMessage());
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
}
