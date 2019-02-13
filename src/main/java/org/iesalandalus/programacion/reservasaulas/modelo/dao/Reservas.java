package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

public class Reservas {

	private static final int MAX_RESERVAS = 30 ;	
	private int numReservas;
	private Reserva[] reservasHechas;
	
	public Reservas() {
		
		numReservas = 0;
		reservasHechas = new Reserva[MAX_RESERVAS];
		
	}
	
	public Reservas(Reservas reservas) {
		
		setReservas(reservas);
		
	}
	
	private void setReservas(Reservas reservas) {
	
		if (reservas == null) {
			throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
		}else {
			this.reservasHechas = copiaProfundaReservas(reservas.reservasHechas);
			this.numReservas = reservas.numReservas;
		}
	}

	private Reserva[] copiaProfundaReservas(Reserva[] Reserva) {
		
		   Reserva[] reservasHechas = new Reserva[Reserva.length];
			for (int i = 0; i < Reserva.length && Reserva[i] != null; i++) {
				reservasHechas[i] = new Reserva(Reserva[i]);
			}
		        
		        return reservasHechas;
	}

	public Reserva[] getReservas() {
		
		return copiaProfundaReservas(reservasHechas) ;
		
	}
	public int getNumReservas() {
		
		return numReservas;
		
	}
	
	public void insertar(Reserva reserva) throws OperationNotSupportedException{
		
		if (reserva ==null) {
			throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
        if (!indiceNoSuperaTamano(indice)) {
            reservasHechas[indice] = reserva;
            numReservas++;
        } else {
            if (indiceNoSuperaCapacidad(indice)) {
            throw new OperationNotSupportedException("La reserva ya existe.");
            } else {
		throw new OperationNotSupportedException("No se aceptan más reservas.");		
            }
        }
	}    
	
	private int buscarIndiceReserva(Reserva reserva) {
		
		int indice = 0;
		boolean reservaEncontrada = false;
		while (indiceNoSuperaTamano(indice)&& !reservaEncontrada) {
			if (reservasHechas[indice].equals(reserva)) {
				reservaEncontrada = true;
			}else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean indiceNoSuperaTamano(int indice) {
		
		return indice < numReservas;
	}
	
	private boolean indiceNoSuperaCapacidad(int indice) {
		
		return indice < MAX_RESERVAS;
	}
	public Reserva buscar(Reserva reserva) {
		
		int indice = 0;
		indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			return reservasHechas[indice];
		}else {
			return null;
		}
	}
	
	public void borrar (Reserva reserva) throws OperationNotSupportedException{
		
		if (reserva == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		int indice = buscarIndiceReserva(reserva);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}else {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}
	
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {
		
		for (int i = indice; i < numReservas - 1; i++) {
            reservasHechas[i] = reservasHechas[i+1];
            }
			reservasHechas[numReservas] = null;
            numReservas--;
	}
	public String []representar() {
		
		String[] representacion = new String[numReservas];
        for (int i = 0; indiceNoSuperaTamano(i); i++) {
                representacion[i] = reservasHechas[i].toString();
        }
        return representacion;
	}
	
	public Reserva[] getReservasProfesor(Profesor profesor) {
		
		Reserva[] reservasProfesor = new Reserva[MAX_RESERVAS];
		int indice = 0;
		if (profesor == null) {
			throw new IllegalArgumentException("");
		}
		for (int i=0; i < numReservas; i++){
			if (reservasHechas[i].getProfesor().equals(profesor)) {
				reservasProfesor[indice] = new Reserva(reservasHechas[i]);
				indice++;
			}
		}
		return reservasProfesor;
		
	}
	
	public Reserva[] getReservasAula(Aula aula) {
		
		Reserva[] reservasAula = new Reserva[MAX_RESERVAS];
		int indice = 0;
		for (int i = 0; i < numReservas; i++){
			if (reservasHechas[i].getAula().equals(aula)) {
				reservasAula[indice] = new Reserva(reservasHechas[i]);
				indice++;
			}
		}
		return reservasAula;
		
	}
	public Reserva[] getReservasPermanencia(Permanencia permanencia) {
		
		Reserva[] reservasPermanencia = new Reserva[MAX_RESERVAS];
		int indice = 0;
		for (int i = 0; i < numReservas; i++){
			if (reservasHechas[i].getPermanencia().equals(permanencia)) {
				reservasPermanencia[indice] = new Reserva(reservasHechas[i]);
				indice++;
			}
		}
		return reservasPermanencia;
		
	}
	
	public boolean consultarDisponibilidad(Aula aula,Permanencia permanencia) {
		

     	if(aula==null)
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
	if(permanencia==null)
            throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
	
        for(int i = 0; i<reservasHechas.length && reservasHechas[i]!=null; i++) {
            
            if(reservasHechas[i].getAula().equals(aula) && reservasHechas[i].getPermanencia().equals(permanencia))
		return false;
	}
	
        return true;
	
    }
	
}
