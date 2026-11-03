package com.ejemplo.mvc.vista;

import java.util.ArrayList;
import java.util.Scanner;

import com.ejemplo.mvc.modelo.Persona;

// Aquí escribiremos la interfaz de usuario.

/*
 * Interactúa con el usuario.
 * 
 * Muestra datos (imprime)
 * 
 * Captura datos (lee)
 * 
 * No contiene la lógica del negocio, ni accede al repositorio.
 * 
 * Sólo se comunica con el controlador
 */

public class PersonaView {
	
	private final Scanner scanner;
	
	public PersonaView(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public int mostrarMenu() {
		System.out.println("\n Sistema de Gestión de Personas");
		System.out.println();
		System.out.println("1. Alta.");
		System.out.println("2. Listar.");
		System.out.println("3. Consultar por ID.");
		System.out.println("4. Modificar.");
		System.out.println("5. Eliminar.");
		System.out.println("0. Salir.");
		System.out.println("---------------------------------");
		System.out.print("Opción: ");
		return leerEntero();
	}
	
	private int leerEntero() {
		while (true) {
			try {
				String linea = scanner.nextLine().trim();
				return Integer.parseInt(linea);
			} catch (NumberFormatException error) {
				System.out.println("\nIngresa un número válido.\n");
			}
		}
	}
	
	public String pedirNombre() {
		System.out.println();
		System.out.print("Nombre: ");
		return scanner.nextLine();
	}
	
	public String pedirApellido() {
		System.out.println();
		System.out.print("Apellido: ");
		return scanner.nextLine();
	}
	
	public int pedirEdad() {
		System.out.println();
		System.out.print("Edad: ");
		return leerEntero();
	}
	
	public Long pedirId() {
		System.out.println();
		System.out.print("Id: ");
		return (long)leerEntero();
	}
	
	public void mostrarListado(ArrayList<Persona> personas) {
		if (personas.isEmpty()) {
			System.out.println();
			System.out.println("(No hay personas registradas)");
		} else {
			System.out.println();
			System.out.println("LISTADO");
			for (Persona p : personas) {
				System.out.println(p);
			}
		}
	}
	
	public void mostrarPersona(Persona persona) {
		System.out.println();
		System.out.println("Encontrada: " + persona);
	}
	
	public void mostrarMensaje(String mensaje) {
		System.out.println();
		System.out.println("! " + mensaje);
	}
	
	public void mostrarError(String mensaje) {
		System.err.println();
		System.err.println("X " + mensaje);
	}
}
