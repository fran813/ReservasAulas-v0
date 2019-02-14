package org.iesalandalus.programacion.reservasaulas.modelo;


import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dao.*;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.*;

public class ModeloReservasAulas {

	private Profesores profesores;
	private Aulas aulas;
	private Reservas reservas;

	public ModeloReservasAulas() {

		profesores = new Profesores();
		aulas = new Aulas();
		reservas = new Reservas();
	}

	public Aula[] getAulas() {

		return aulas.getAulas();
	}

	public int getNumAulas() {

		return aulas.getNumAulas();
	}

	public String[] representarAulas() {

		return aulas.representar();
	}

	public Aula buscarAula(Aula aula) {

		return aulas.buscar(aula);
	}

	public void insertarAula(Aula aula)throws OperationNotSupportedException {

		aulas.insertar(aula);
	}

	public void borrarAula(Aula aula) throws OperationNotSupportedException {

		aulas.borrar(aula);
	}

	public Profesor[] getProfesores() {

		return profesores.getProfesores();
	}

	public int getNumProfesores() {

		return profesores.getNumProfesores();
	}

	public String[] representarProfesores() {

		return profesores.representar();
	}

	public Profesor buscarProfesor(Profesor profesor) {

		return profesores.buscar(profesor);
	}

	public void insertarProfesor(Profesor profesor)throws OperationNotSupportedException {

		profesores.insertar(profesor);
	}

	public void borrarProfesor(Profesor profesor)throws OperationNotSupportedException {

		profesores.borrar(profesor);
	}

	public Reserva[] getReservas() {

		return reservas.getReservas();
	}

	public int getnumReservas() {

		return reservas.getNumReservas();
	}

	public String[] representarReservas() {

		return reservas.representar();
	}

	public Reserva buscarReserva(Reserva reserva) {

		return reservas.buscar(reserva);
	}

	public void realizarReserva(Reserva reserva)throws OperationNotSupportedException {

		reservas.insertar(reserva);
	}

	public void anularReserva(Reserva reserva)throws OperationNotSupportedException {

		reservas.borrar(reserva);
	}

	public Reserva[] getReservasAulas(Aula aula) {

		return reservas.getReservasAula(aula);
	}

	public Reserva[] getReservasProfesor(Profesor profesor) {

		return reservas.getReservasProfesor(profesor);
	}

	public Reserva[] getReservasPermanencia(Permanencia permanencia) {

		return reservas.getReservasPermanencia(permanencia);
	}

	public boolean conultarDisponibilidad(Aula aula, Permanencia permanencia) {

		return reservas.consultarDisponibilidad(aula, permanencia);
	}
}