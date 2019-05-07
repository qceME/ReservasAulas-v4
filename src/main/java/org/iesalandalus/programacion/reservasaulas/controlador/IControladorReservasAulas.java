/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.controlador;

import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;

/**
 *
 * @author Quique
 */
public interface IControladorReservasAulas extends IVistaReservasAulas {

    void anularReserva(Reserva r) throws OperationNotSupportedException;

    void borrarAula(Aula a) throws OperationNotSupportedException;

    void borrarProfesor(Profesor p) throws OperationNotSupportedException;

    Aula buscarAula(Aula a);

    Profesor buscarProfesor(Profesor p);

    void comenzar();

    boolean consultarDisponibilidad(Aula a, Permanencia p);

    List<Reserva> getReservasAula(Aula a);

    List<Reserva> getReservasPermanencia(Permanencia p);

    List<Reserva> getReservasProfesor(Profesor p);

    void insertarAula(Aula a) throws OperationNotSupportedException;

    void insertarProfesor(Profesor p) throws OperationNotSupportedException;

    void realizarReserva(Reserva r) throws OperationNotSupportedException;

    List<String> representarAulas();

    List<String> representarProfesores();

    List<String> representarReservas();

    void salir();
    
}
