
package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import java.util.Objects;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;

/**
 *
 * @author quique
 */
public class Reserva implements Serializable{
    Profesor profesor;
    Aula aula;
    Permanencia permanencia;
    
    public Reserva (Profesor p, Aula a, Permanencia perm){
        
        setProfesor(p);
        setAula(a);
        setPermanencia(perm);
           
    }
    
    public Reserva (Reserva r){
        
        if (r==null) throw new IllegalArgumentException("No se puede copiar una reserva nula.");
        this.profesor=r.getProfesor();
        this.aula=r.getAula();
        this.permanencia=r.getPermanencia();
        
    }

    public Profesor getProfesor() {
        return profesor;
    }

    private void setProfesor(Profesor profesor) {
                if (profesor==null) throw new IllegalArgumentException ("La reserva debe estar a nombre de un profesor.");

        this.profesor = new Profesor(profesor);
    }

    public Aula getAula() {
        return aula;
    }

    private void setAula(Aula aula) {
                if (aula==null) throw new IllegalArgumentException ("La reserva debe ser para un aula concreta.");

        this.aula =new Aula(aula);
    }

    public Permanencia getPermanencia() {
            if (permanencia instanceof PermanenciaPorHora) {
                
		return new PermanenciaPorHora((PermanenciaPorHora) permanencia);
             } else {
                
		return new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
}
    }

    private void setPermanencia(Permanencia permanencia) {
        
                if (permanencia==null) throw new IllegalArgumentException ("La reserva se debe hacer para una permanencia concreta.");
                
                if (permanencia instanceof PermanenciaPorHora)
                    
			this.permanencia = new PermanenciaPorHora((PermanenciaPorHora) permanencia);
                
		if (permanencia instanceof PermanenciaPorTramo)
                    
                        this.permanencia = new PermanenciaPorTramo((PermanenciaPorTramo) permanencia);
    }
    
    public float getPuntos(){
        return aula.getPuntos() + permanencia.getPuntos();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.profesor);
        hash = 47 * hash + Objects.hashCode(this.aula);
        hash = 47 * hash + Objects.hashCode(this.permanencia);
        return hash;
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
        final Reserva other = (Reserva) obj;
        if (!Objects.equals(this.aula, other.aula)) {
            return false;
        }
        if (!Objects.equals(this.permanencia, other.permanencia)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[profesor=" + profesor + ", aula=" + aula + ", permanencia=" + permanencia + ", puntos=" + getPuntos()+ "]";
    }
    
    
    
}
