package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;


public class Aulas {
	private static final int MAX_AULAS = 30 ;	
	private int numAulas;
	private Aula[] cantidadAulas;
	
	public Aulas() {
		
		numAulas = 0;
		cantidadAulas = new Aula[MAX_AULAS];
		
	}
	
	public Aulas(Aulas aulas) {
		
		setAulas(aulas);
		
	}
	
	private void setAulas(Aulas aulas) {
	
		if (aulas == null) {
			throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
		}else {
			this.cantidadAulas = copiaProfundaAulas(aulas.cantidadAulas);
			this.numAulas = aulas.numAulas;
		}
	}

	private Aula[] copiaProfundaAulas(Aula[] Aula) {
		
		   Aula[] cantidadAulas = new Aula[Aula.length];
			for (int i = 0; i < Aula.length && Aula[i] != null; i++) {
				cantidadAulas[i] = new Aula(Aula[i]);
			}
		        
		        return cantidadAulas;
	}

	public Aula[] getAulas() {
		
		return copiaProfundaAulas(cantidadAulas) ;
		
	}
	public int getNumAulas() {
		
		return numAulas;
		
	}
	
	public void insertar(Aula aula) throws OperationNotSupportedException{
		
		if (aula ==null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
		}
		int indice = buscarIndiceAula(aula);
        if (!indiceNoSuperaTamano(indice)) {
            cantidadAulas[indice] = aula;
            numAulas++;
        } else {
            if (indiceNoSuperaCapacidad(indice)) {
            throw new OperationNotSupportedException("El aula ya existe.");
            } 
        }
	}    
	
	private int buscarIndiceAula(Aula aula) {
		
		int indice = 0;
		boolean aulaEncontrada = false;
		while (indiceNoSuperaTamano(indice)&& !aulaEncontrada) {
			if (cantidadAulas[indice].equals(aula)) {
				aulaEncontrada = true;
			}else {
				indice++;
			}
		}
		return indice;
	}
	
	private boolean indiceNoSuperaTamano(int indice) {
		
		return indice < numAulas;
	}
	
	private boolean indiceNoSuperaCapacidad(int indice) {
		
		return indice < MAX_AULAS;
	}
	public Aula buscar(Aula aula) {
		
		int indice = 0;
		indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			return cantidadAulas[indice];
		}else {
			return null;
		}
	}
	
	public void borrar (Aula aula) throws OperationNotSupportedException{
		
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
		}
		int indice = buscarIndiceAula(aula);
		if (indiceNoSuperaTamano(indice)) {
			desplazarUnaPosicionHaciaIzquierda(indice);
		}else {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}
	
private void desplazarUnaPosicionHaciaIzquierda(int indice) {

	for (int i = indice; i < numAulas - 1; i++) {
		cantidadAulas[i] = cantidadAulas[i + 1];
	}
	cantidadAulas[numAulas] = null;
	numAulas--;
}
	public String []representar() {
		
		String[] representacion = new String[numAulas];
        for (int i = 0; indiceNoSuperaTamano(i); i++) {
                representacion[i] = cantidadAulas[i].toString();
        }
        return representacion;
	}
}
