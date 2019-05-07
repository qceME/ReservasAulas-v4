package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 *
 * @author Quique
 */

public class PermanenciaPorHora extends Permanencia {

	private static final int PUNTOS = 3;
	private static final int HORA_INICIO = 8;
	private static final int HORA_FIN = 22;
	private static final DateTimeFormatter FORMATO_HORA = DateTimeFormatter.ofPattern("HH:mm");
	private LocalTime hora;

	public PermanenciaPorHora(LocalDate d, LocalTime h) {
		super(d);
		setHora(h);
	}

	public PermanenciaPorHora(String d, LocalTime h) {
		super(d);
		setHora(h);
	}

	public PermanenciaPorHora(LocalDate d, String h) {
		super(d);
		setHora(h);
	}

	public PermanenciaPorHora(String d, String h) {
		super(d);
		setHora(h);
	}

	public PermanenciaPorHora(PermanenciaPorHora pph) {
		super();
		if (pph == null) {
			throw new IllegalArgumentException("No se puede copiar una permanencia nula.");
		}
		setDia(pph.getDia());
		setHora(pph.getHora());
	}

	public LocalTime getHora() {
		return this.hora;
	}

	private void setHora(LocalTime h) {
		if (h == null) throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
		
		if (h.getHour() > HORA_INICIO && 
                    h.getHour() < HORA_FIN) {
                            if (h.getMinute() == 0) {
				hora = h;
                            } else {throw new IllegalArgumentException("La hora de una permanencia debe ser una hora en punto.");}
                }else {
			throw new IllegalArgumentException(
					"La hora de una permanencia debe estar comprendida entre las 8 y las 22.");
		}
	}

	private void setHora(String h) {
		if (h == null) {
			throw new IllegalArgumentException("La hora de una permanencia no puede ser nula.");
		}
		try {
			this.setHora(LocalTime.parse(h, FORMATO_HORA));
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("El formato de la hora de la permanencia no es correcto.");
		}
	}

	public int getPuntos() {
		return PUNTOS;
	}

	@Override
	public int hashCode() {
            return Objects.hash(dia,hora);
		
	}

	@Override
	public boolean equals(Object obj) {
             if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
       }
        if (getClass() != obj.getClass()) {
            return false;
         }
        final PermanenciaPorHora other=(PermanenciaPorHora)  obj;

        if(getDia().equals(other.getDia()) &&
           getHora().equals(other.getHora()))
            return true;
	return false;
		
	}

	@Override
	public String toString() {
		return "[dia="+dia.format(FORMATO_DIA)+", hora="+hora.format(FORMATO_HORA)+"]";
	}

}