package com.ejemplo.mvc;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import com.ejemplo.mvc.controlador.PersonaController;
import com.ejemplo.mvc.modelo.PersonaRepository;
import com.ejemplo.mvc.modelo.PersonaRepositoryFactory;
import com.ejemplo.mvc.modelo.PersonaService;
import com.ejemplo.mvc.vista.PersonaView;

public class MVC {

	public static void main(String[] args) {

		String repositorioTipo = "MEMORY";
		Scanner conf = null;
		File file = new File("config.txt");
		try {
			if (file.exists()) {
				conf = new Scanner(file);
				while (conf.hasNext()) {
					String linea = conf.nextLine();
					String[] params = linea.split(":");
					if (params[0].trim().equalsIgnoreCase("REPOSITORIO")) {
						repositorioTipo = params[1].trim();
					}
					if (params[0].trim().equalsIgnoreCase("OTRO_PARAMETRO2")) {
						System.out.println("***************");
						System.out.println(params[1].trim());
						System.out.println("***************");
					}
				}
			} 
		} catch (IOException error) {
			System.out.println("Error: " + error.getMessage());
		}	
		
		PersonaRepository repositorio 
			= PersonaRepositoryFactory.crear(
					PersonaRepositoryFactory.Tipo.valueOf(repositorioTipo));
		PersonaService servicio = new PersonaService(repositorio);
		Scanner scanner = new Scanner(System.in);
		PersonaView vista = new PersonaView(scanner);
		PersonaController controlador = 
				new PersonaController(servicio, vista);
		controlador.iniciar();
		scanner.close();
	}

}
