
package org.iesalandalus.programacion.reservasaulas.modelo.ficheros;

import java.io.IOException;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.Aulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.Profesores;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.Reservas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;

/**
 *
 * @author quique
 */

public class ModeloReservasAulas implements IModeloReservasAulas {
    
    private Profesores profesores;
    private Aulas aulas;
    private Reservas reservas;
    
    
    public ModeloReservasAulas(){
        
        profesores=new Profesores();
        aulas=new Aulas();
        reservas=new Reservas();       
    }

    @Override
    public List<Profesor> getProfesores() {
        return profesores.getProfesores();
    }
    
    @Override
    public int getNumProfesores() {
        return profesores.getNumProfesores();
    }
    
    @Override
    public void insertarProfesor(Profesor p) throws OperationNotSupportedException {
	profesores.insertar(p);
    }
    @Override
    public void borrarProfesor(Profesor p) throws OperationNotSupportedException {
	profesores.borrar(p);
    }
	
    @Override
    public Profesor buscarProfesor(Profesor p) {
	return profesores.buscar(p);
    }
	
    @Override
    public List<String> representarProfesores() {
	return profesores.representar();
    }
    
    @Override
    public List<Aula> getAulas() {
        return aulas.getAulas();
    }
    
    @Override
    public int getNumAulas() {
        return aulas.getNumAulas();
    }
    @Override
    public void insertarAula(Aula a) throws OperationNotSupportedException {
    aulas.insertar(a);
    }
    @Override
    public void borrarAula(Aula a) throws OperationNotSupportedException {
    aulas.borrar(a);
    }
	
    @Override
    public Aula buscarAula(Aula a) {
    return aulas.buscar(a);
    }
	
    @Override
    public List<String> representarAulas() {
    return aulas.representar();
    }
    

    @Override
    public List<Reserva> getReservas() {
        return reservas.getReservas();
    }
    
    @Override
    public int getNumReservas() {
        return reservas.getNumReservas();
    }
    @Override
    public void realizarReserva(Reserva r) throws OperationNotSupportedException {
	reservas.insertar(r);
    }
    @Override
    public void anularReserva(Reserva r) throws OperationNotSupportedException {
	reservas.borrar(r);
    }
	
    @Override
    public Reserva buscarReserva(Reserva r) {
	return reservas.buscar(r);
    }
	
    @Override
    public List<String> representarReservas() {
	return reservas.representar();
    }
    
    
    
    @Override
    public List<Reserva> getReservasAula(Aula a){
        
        return reservas.getReservasAula(a);
        
    }
    
    @Override
    public List<Reserva> getReservasProfesor(Profesor p){
        return reservas.getReservasProfesor(p);
        
    }
    
    @Override
    public List<Reserva> getReservasPermanencia(Permanencia p){
        return reservas.getReservasPermanencia(p);
        
    }
    
    @Override
    public boolean consultarDisponibilidad(Aula a,Permanencia p){
        return reservas.consultarDisponibilidad(a, p);
        
    }

    @Override
    public void leerAulas() throws IOException {
        
        aulas.leer();
    }

    @Override
    public void escribirAulas() {
                aulas.escribir();

    }

    @Override
    public void leerProfesores() {
                profesores.leer();

       
    }

    @Override
    public void escribirProfesores() {
                        profesores.escribir();

    }

    @Override
    public void leerReservas() throws IOException {
                reservas.leer();

    }

    @Override
    public void escribirReservas() {
                        reservas.escribir();

    }

  
}
