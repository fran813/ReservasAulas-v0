package org.iesalandalus.programacion.reservasaulas.modelo.dao;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

public class Profesores {
	
		private static final int MAX_PROFESORES = 30 ;	
		private int numProfesores;
		private Profesor[] cantidadProfesores;
		public Profesores() {
			
			numProfesores = 0;
			cantidadProfesores = new Profesor[MAX_PROFESORES];
			
		}
		
		public Profesores(Profesores profesores) {
			
			setProfesores(profesores);
			
		}
		
		private void setProfesores(Profesores profesores) {
		
			if (profesores == null) {
				throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
			}else {
				this.cantidadProfesores = copiaProfundaProfesores(profesores.cantidadProfesores);
				this.numProfesores = profesores.numProfesores;
			}
		}

		private Profesor[] copiaProfundaProfesores(Profesor[] Profesor) {
			
			   Profesor[] cantidadProfesores = new Profesor[Profesor.length];
				for (int i = 0; i < Profesor.length && Profesor[i] != null; i++) {
					cantidadProfesores[i] = new Profesor(Profesor[i]);
				}
			        
			        return cantidadProfesores;
		}

		public Profesor[] getProfesores() {
			
			return copiaProfundaProfesores(cantidadProfesores) ;
			
		}
		public int getNumProfesores() {
			
			return numProfesores;
			
		}
		
		public void insertar(Profesor profesor) throws OperationNotSupportedException{
			
			if (profesor ==null) {
				throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
			}
			int indice = buscarIndiceProfesor(profesor);
	        if (!indiceNoSuperaTamano(indice)) {
	            cantidadProfesores[indice] = profesor;
	            numProfesores++;
	        } else {
	            if (indiceNoSuperaCapacidad(indice)) {
	            throw new OperationNotSupportedException("El profesor ya existe.");
	            } 
	        }
		}    
		
		private int buscarIndiceProfesor(Profesor profesor) {
			
			int indice = 0;
			boolean profesorEncontrado = false;
			while (indiceNoSuperaTamano(indice)&& !profesorEncontrado) {
				if (cantidadProfesores[indice].equals(profesor)) {
					profesorEncontrado = true;
				}else {
					indice++;
				}
			}
			return indice;
		}
		
		private boolean indiceNoSuperaTamano(int indice) {
			
			return indice < numProfesores;
		}
		
		private boolean indiceNoSuperaCapacidad(int indice) {
			
			return indice < MAX_PROFESORES;
		}
		public Profesor buscar(Profesor profesor) {
			
			int indice = 0;
			indice = buscarIndiceProfesor(profesor);
			if (indiceNoSuperaTamano(indice)) {
				return cantidadProfesores[indice];
			}else {
				return null;
			}
		}
		
		public void borrar (Profesor profesor) throws OperationNotSupportedException{
			
			if (profesor == null) {
				throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
			}
			int indice = buscarIndiceProfesor(profesor);
			if (indiceNoSuperaTamano(indice)) {
				desplazarUnaPosicionHaciaIzquierda(indice);
			}else {
				throw new OperationNotSupportedException("El profesor a borrar no existe.");
			}
		}
		
	private void desplazarUnaPosicionHaciaIzquierda(int indice) {

		for (int i = indice; i < numProfesores - 1; i++) {
			cantidadProfesores[i] = cantidadProfesores[i + 1];
		}
		cantidadProfesores[numProfesores] = null;
		numProfesores--;
	}
		public String []representar() {
			
			String[] representacion = new String[numProfesores];
	        for (int i = 0; indiceNoSuperaTamano(i); i++) {
	                representacion[i] = cantidadProfesores[i].toString();
	        }
	        return representacion;
		}
	}
