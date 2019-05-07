/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo;

import java.io.IOException;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;

/**
 *
 * @author Quique
 */
public interface IModeloReservasAulas {

    void anularReserva(Reserva r) throws OperationNotSupportedException;

    void borrarAula(Aula a) throws OperationNotSupportedException;

    void borrarProfesor(Profesor p) throws OperationNotSupportedException;

    Aula buscarAula(Aula a);

    Profesor buscarProfesor(Profesor p);

    Reserva buscarReserva(Reserva r);

    boolean consultarDisponibilidad(Aula a, Permanencia p);

    List<Aula> getAulas();

    int getNumAulas();

    int getNumProfesores();

    int getNumReservas();

    List<Profesor> getProfesores();

    List<Reserva> getReservas();

    List<Reserva> getReservasAula(Aula a);

    List<Reserva> getReservasPermanencia(Permanencia p);

    List<Reserva> getReservasProfesor(Profesor p);

    void insertarAula(Aula a) throws OperationNotSupportedException;

    void insertarProfesor(Profesor p) throws OperationNotSupportedException;

    void realizarReserva(Reserva r) throws OperationNotSupportedException;

    List<String> representarAulas();

    List<String> representarProfesores();

    List<String> representarReservas();
    
        void leerAulas() throws IOException;

	void escribirAulas();
        
        void leerProfesores();

	void escribirProfesores();
        
        void leerReservas() throws IOException;

	void escribirReservas();
    
}
