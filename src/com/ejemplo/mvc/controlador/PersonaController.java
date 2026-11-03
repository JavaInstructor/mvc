package com.ejemplo.mvc.controlador;

import com.ejemplo.mvc.modelo.Persona;
import com.ejemplo.mvc.modelo.PersonaService;
import com.ejemplo.mvc.vista.PersonaView;

// Responsable de la coordinación.

/*
 * Coordina las acciones entre la vista y el modelo.
 * 
 * Recibe opciones del usuario desde la vista.
 * 
 * Llama al servicio.
 * 
 * Devuelve el resultado para mostrarlo en la vista.
 * 
 * Traduce excepciones del sistema.
 * 
 */

public class PersonaController {
	
	private final PersonaService servicio;
	private final PersonaView vista;
	
	public PersonaController(PersonaService servicio, PersonaView vista) {
		this.servicio = servicio;
		this.vista = vista;
	}

	public void iniciar() {
		int opcion;
		do {
			opcion = vista.mostrarMenu();
			try {
				switch (opcion) {
					case 1: 
						crear();
						break;
					case 2: 
						listar();
						break;
					case 3: 
						buscar();
						break;
					case 4: 
						modificar();
						break;
					case 5: 
						eliminar();
						break;
					case 0: 
						vista.mostrarMensaje("Hasta luego. Gracias por usar nuestro servicio.");
						break;
					default:
						vista.mostrarError("Opción no válida.");
				}
			} catch (IllegalArgumentException error) {
				vista.mostrarError(error.getMessage());
			} catch (Exception error) {
				vista.mostrarError("Error inesperado: " + error.getMessage());
			}
			
		} while (opcion != 0);
	}
	
	private void crear() {
		String nombre = vista.pedirNombre();
		String apellido = vista.pedirApellido();
		int edad = vista.pedirEdad();
		Persona persona = servicio.crear(nombre, apellido, edad);
		vista.mostrarMensaje("Persona creada: " + persona);
	}
	
	private void listar() {
		vista.mostrarListado(servicio.listar());
	}
	
	private void buscar() {
		Long id = vista.pedirId();
		Persona persona = servicio.buscar(id);
		vista.mostrarPersona(persona);
	}
	
	private void modificar() {
		Long id = vista.pedirId();
		Persona persona = servicio.buscar(id);
		vista.mostrarPersona(persona);
		String nombre = vista.pedirNombre();
		String apellido = vista.pedirApellido();
		int edad = vista.pedirEdad();
		persona = servicio.actualizar(id, nombre, apellido, edad);
		vista.mostrarMensaje("Persona actualizada: " + persona);
	}
	
	private void eliminar() {
		Long id = vista.pedirId();
		if (servicio.eliminar(id)) {
			vista.mostrarMensaje("Persona eliminada con éxito.");
		} else {
			vista.mostrarError("La persona no pudo ser eliminada.");
		}
	}
}
