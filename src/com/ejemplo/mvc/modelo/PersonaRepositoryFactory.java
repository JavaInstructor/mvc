	package com.ejemplo.mvc.modelo;

public class PersonaRepositoryFactory {
	
	public enum Tipo {
		ARCHIVO,
		BD,
		MEMORY
	}
	
	private PersonaRepositoryFactory() {
		
	}
	
	public static PersonaRepository crear(Tipo tipo) {
		if (tipo != null) {
			switch (tipo) {
				case ARCHIVO: 
					return new PersonaRepositoryFile();
				case BD:
					return new PersonaRepositoryDB();
				case MEMORY:
					return new PersonaRepositoryMemory();
				default:	
					System.out.println("El tipo no es soportado.");
			}
		} else {
			System.out.println("El tipo de repositorio es obligatorio.");
		}
		return null;
	}

}
