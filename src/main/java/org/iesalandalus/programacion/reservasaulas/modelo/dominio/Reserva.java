package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

public class Reserva {

	private Profesor profesor;
	private Aula aula;
	private Permanencia permanencia;

	public Reserva(Profesor profesor, Aula aula, Permanencia permanencia) {

		setProfesor(profesor);
		setAula(aula);
		setPermanencia(permanencia);

	}

	public Reserva(Reserva reserva) {

		if (reserva == null) {
			throw new IllegalArgumentException("No se puede copiar una reserva nula.");
		}

		setProfesor(reserva.profesor);
		setAula(reserva.aula);
		setPermanencia(reserva.permanencia);

	}
	
	private void setProfesor(Profesor profesor) {
		
		if (profesor == null) {
			throw new IllegalArgumentException("La reserva debe estar a nombre de un profesor.");
		}
		
		this.profesor = profesor;
	}
	
	public Profesor getProfesor(){
		
		return profesor;
		
	}
	
	private void setAula(Aula aula) {
		
		if (aula == null) {
			throw new IllegalArgumentException("La reserva debe ser para un aula concreta.");
		}
		
		this.aula = aula;
	}
	
	public Aula getAula() {
		
		return aula;
	}
	
	private void setPermanencia(Permanencia permanencia) {
		
		if (permanencia == null) {
			throw new IllegalArgumentException("La reserva se debe hacer para una permanencia concreta.");
		}
		
		this.permanencia = permanencia;
		
	}
	
	public Permanencia getPermanencia() {
		
		return permanencia;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aula == null) ? 0 : aula.hashCode());
		result = prime * result + ((permanencia == null) ? 0 : permanencia.hashCode());
		result = prime * result + ((profesor == null) ? 0 : profesor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		if (aula == null) {
			if (other.aula != null)
				return false;
		} else if (!aula.equals(other.aula))
			return false;
		if (permanencia == null) {
			if (other.permanencia != null)
				return false;
		} else if (!permanencia.equals(other.permanencia))
			return false;
		if (profesor == null) {
			if (other.profesor != null)
				return false;
		} else if (!profesor.equals(other.profesor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[profesor=" + profesor +", aula=" + aula + ", permanencia=" + permanencia + "]";
	}
	
}
