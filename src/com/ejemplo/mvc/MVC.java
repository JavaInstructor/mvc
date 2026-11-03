package com.ejemplo.mvc;

import java.util.Scanner;

import com.ejemplo.mvc.controlador.PersonaController;
import com.ejemplo.mvc.modelo.PersonaRepository;
import com.ejemplo.mvc.modelo.PersonaService;
import com.ejemplo.mvc.vista.PersonaView;

public class MVC {

	public static void main(String[] args) {

		PersonaRepository repositorio = new PersonaRepository();
		PersonaService servicio = new PersonaService(repositorio);
		Scanner scanner = new Scanner(System.in);
		PersonaView vista = new PersonaView(scanner);
		PersonaController controlador = 
				new PersonaController(servicio, vista);
		controlador.iniciar();
		scanner.close();
	}

}
