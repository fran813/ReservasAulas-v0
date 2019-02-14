package org.iesalandalus.programacion.reservasaulas.vista;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.utilidades.Entrada;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;

public class IUTextual {
	
private static final String ERROR = "ERROR: ";
private static final String NOMBRE_VALIDO = "";
private static final String CORREO_VALIDO = "";
private ModeloReservasAulas modelo;	

	public IUTextual() {
		modelo = new ModeloReservasAulas();
		Opcion.setVista(this);
	}

	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
	public void salir() {
		System.out.println("Hasta Prontooo!!!");
	}
	
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar Aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.insertarAula(aula);
			System.out.println("Aula insertada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
			Aula aula = Consola.leerAula();
			modelo.borrarAula(aula);
			System.out.println("Aula borrada correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
		Aula aula = null;
		try {
			aula = Consola.leerAula();
			aula = modelo.buscarAula(aula);
			if (aula != null) {
				System.out.println("El aula buscada es: " + aula);
			} else {
				System.out.println("No existe ningún aula.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void listarAulas() {
		Consola.mostrarCabecera("Listar Aulas");
		String[] Aulas = modelo.representarAulas();
		if (Aulas.length > 0) {
			for (String aula : Aulas) {
				System.out.println(aula);
			}
		} else {
			System.out.println("No hay aulas que listar.");
		}
	}
	public void insertarProfesor() {
		Consola.mostrarCabecera("Insertar Profesor");
		Profesor profesor = Consola.leerProfesor();
		String correo;
			if (profesor.getNombre().matches(NOMBRE_VALIDO)) { 
				System.out.print("Escrie un correo valido");
				correo = Entrada.cadena();
				if (profesor.getCorreo().matches(CORREO_VALIDO));
					profesor.setCorreo(correo);
				try {
			modelo.insertarProfesor(profesor);			
			System.out.println("Profesor insertado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
			}
		}
	}

	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
			Profesor profesor = Consola.leerProfesor();
			modelo.borrarProfesor(profesor);
			System.out.println("Profesor borrado correctamente.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void buscarProfesor() {
		Consola.mostrarCabecera("Buscar profesor");
		Profesor profesor = null;
		try {
			profesor = Consola.leerProfesor();
			profesor = modelo.buscarProfesor(profesor);
			if (profesor != null) {
				System.out.println("El aula buscada es: " + profesor);
			} else {
				System.out.println("No existe ningún profesor con ese nombre.");
			}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	public void listarProfesor() {
		Consola.mostrarCabecera("Listar Profesor");
		String[] Profesores = modelo.representarProfesores();
		if (Profesores.length > 0) {
			for (String profesor : Profesores) {
				System.out.println(profesor);
			}
		} else {
			System.out.println("No hay profesor que listar.");
		}
	}
	
	public void realizarReserva() {
		Consola.mostrarCabecera("Realizar Reserva");
		try {
			Reserva reserva = leerReserva(Consola.leerProfesor());
			modelo.realizarReserva(reserva);
			System.out.println("Has realizado correctamente la reserva.");
		} catch (OperationNotSupportedException|IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}
	}
	
	private Reserva leerReserva(Profesor profesor) {
		
		  Aula aula = Consola.leerAula();
          Permanencia permanencia = new Permanencia(Consola.leerDia(), Consola.leerTramo());
          return new Reserva(profesor, aula, permanencia);
	}
	
}
