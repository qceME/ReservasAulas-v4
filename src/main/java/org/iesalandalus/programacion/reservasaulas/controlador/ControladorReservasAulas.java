/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

import java.io.IOException;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.IModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.vista.iutextual.VistaReservasAulas;

/**
 *
 * @author Quique
 */
public class ControladorReservasAulas implements IControladorReservasAulas {

	private IModeloReservasAulas modelo;
	private IVistaReservasAulas vista;
	
	public ControladorReservasAulas(IModeloReservasAulas mra, IVistaReservasAulas vra) {
		modelo = mra;
		vista = vra;
                vista.setControlador(this);
               
	}
	
    @Override
	public void comenzar() {
		
            try {
                modelo.leerAulas();
            } catch (IOException ex) {
                Logger.getLogger(ControladorReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
            }
		modelo.leerProfesores();
            try {
                modelo.leerReservas();
            } catch (IOException ex) {
                Logger.getLogger(ControladorReservasAulas.class.getName()).log(Level.SEVERE, null, ex);
            }
            vista.comenzar();
	}
	
    @Override
	public void salir() {
		
                modelo.escribirAulas();
		modelo.escribirProfesores();
		modelo.escribirReservas();
	}
	
    @Override
	public void insertarAula(Aula a) throws OperationNotSupportedException{
		modelo.insertarAula(a);
	}
	
    @Override
	public void borrarAula(Aula a) throws OperationNotSupportedException {
		modelo.borrarAula(a);
	}
	
    @Override
	public Aula buscarAula(Aula a) {
		return modelo.buscarAula(a);
	}
	
    @Override
	public List<String> representarAulas() {
		return modelo.representarAulas();
	}
            @Override
	public List<Aula> getAulas(){
		return modelo.getAulas();
	}
                    

	
    @Override
	public void insertarProfesor(Profesor p) throws OperationNotSupportedException {
		modelo.insertarProfesor(p);
	}
	
    @Override
	public void borrarProfesor(Profesor p) throws OperationNotSupportedException {
		modelo.borrarProfesor(p);
	}
	
    @Override
	public Profesor buscarProfesor(Profesor p) {
		return modelo.buscarProfesor(p);
	}
	
    @Override
	public List<String> representarProfesores() {
		return modelo.representarProfesores();
	}
	
    @Override
	public void realizarReserva(Reserva r) throws OperationNotSupportedException {
		modelo.realizarReserva(r);
	}
	
    @Override
	public void anularReserva(Reserva r) throws OperationNotSupportedException {
		modelo.anularReserva(r);
	}
	
    @Override
	public List<String> representarReservas() {
		return modelo.representarReservas();
	}
	
    @Override
	public List<Reserva> getReservasAula(Aula a) {
		return modelo.getReservasAula(a);
	}
	
    @Override
	public List<Reserva> getReservasProfesor(Profesor p) {
		return modelo.getReservasProfesor(p);
	}
	
    @Override
	public List<Reserva> getReservasPermanencia(Permanencia p) {
		return modelo.getReservasPermanencia(p);
	}
	
    @Override
	public boolean consultarDisponibilidad(Aula a, Permanencia p) {
		return modelo.consultarDisponibilidad(a, p);
	}

        //Me pide implementar el compilador esto cuando ya están arriba, tendrá su explicación ya que algo tendré mal pero bueno...
    @Override
    public void anularReserva() throws OperationNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setControlador(IControladorReservasAulas controlador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscarAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscarProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarDisponibilidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarAulas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarProfesores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservasAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservasPermanencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservasProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void realizarReserva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Profesor> getProfesores() {
		return modelo.getProfesores();
    }



}