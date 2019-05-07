package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author Quique
 */
public class PermanenciaPorTramo extends Permanencia {

	private static final int PUNTOS = 10;
	private Tramo tramo;

	
	public PermanenciaPorTramo(LocalDate d, Tramo t) {
		super(d);
		setTramo(t);
	}

	
	public PermanenciaPorTramo(String d, Tramo t) {
		super(d);
		setTramo(t);
	}

	
	public PermanenciaPorTramo(PermanenciaPorTramo ppt) {
		if(ppt==null) throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
                
		setDia(ppt.getDia());
		setTramo(ppt.getTramo());
	}

	public Tramo getTramo() {
		return tramo;
	}

	
	private void setTramo(Tramo t) {
		if(t==null) throw new IllegalArgumentException("El tramo de una permanencia no puede ser nulo.");
		this.tramo = t;
	}

	
	public int getPuntos() {
		return PUNTOS;
	}

	
	public int hashCode() {
		
            return Objects.hash(dia,tramo);
	}

@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
                
		final PermanenciaPorTramo other = (PermanenciaPorTramo) obj;
                
		if  (this.getDia().equals(other.getDia()) && 
                     this.getTramo().equals(other.getTramo()))
			return true;
                return false;
}
	



        

	
	public String toString() {
		return "[dia=" + getDia().format(FORMATO_DIA) + ", tramo=" + tramo + "]";
	}

}