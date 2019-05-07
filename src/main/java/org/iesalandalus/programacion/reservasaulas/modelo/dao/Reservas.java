
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;

/**
 *
 * @author quique
 */
public class Reservas {   
    

    private static final float MAX_PUNTOS_PROFESOR_MES = 200;
    private List<Reserva> coleccionReservas;
    private static final String NOMBRE_FICHERO_RESERVAS = ".\\ficheros\\reservas.dat";
    
    public Reservas(){
        coleccionReservas=new ArrayList<>();
  
    }
    public Reservas(Reservas a) throws IllegalArgumentException{
        setReservas(a);
        
    }
    
    
    private void setReservas (Reservas a) throws IllegalArgumentException{
        if (a == null) throw new IllegalArgumentException("No se pueden copiar reservas nulas.");
        coleccionReservas = copiaProfundaReservas(a.coleccionReservas);
    }
    
    
    private List<Reserva> copiaProfundaReservas(List<Reserva> reservas) {
		List<Reserva> otrasReservas = new ArrayList<>();
		for (Reserva reservanew: reservas) {
                        otrasReservas.add(new Reserva(reservanew));

		}
		return otrasReservas;
	}
    
    public List<Reserva> getReservas() {
		return copiaProfundaReservas(coleccionReservas);
	}

    public int getNumReservas() {
        return coleccionReservas.size();
    }
    

    public void insertar(Reserva r) throws OperationNotSupportedException{
		if (r == null) throw new IllegalArgumentException("No se puede realizar una reserva nula.");
		
		if (coleccionReservas.contains(r)) throw new OperationNotSupportedException("La reserva ya existe.");
                        
                if (getPuntosGastadosReserva(r) > MAX_PUNTOS_PROFESOR_MES) throw new OperationNotSupportedException("Esta reserva excede los puntos máximos por mes para dicho profesor.");
                        
                if (!esMesSiguienteOPosterior(r)) throw new OperationNotSupportedException("Sólo se pueden hacer reservas para el mes que viene o posteriores.");
                
		Reserva reservaInsertar = getReservaDia(r.getPermanencia().getDia());
                        if (reservaInsertar != null) {
			if (reservaInsertar.getPermanencia() instanceof PermanenciaPorTramo &&
                            r.getPermanencia() instanceof PermanenciaPorHora) throw new OperationNotSupportedException("Ya se ha realizado una reserva por tramo para este día y aula.");
			
			if (reservaInsertar.getPermanencia() instanceof PermanenciaPorHora &&
                            r.getPermanencia() instanceof PermanenciaPorTramo) throw new OperationNotSupportedException("Ya se ha realizado una reserva por hora para este día y aula.");
			
		}
		coleccionReservas.add(new Reserva(r));
}

 

                
    private boolean esMesSiguienteOPosterior(Reserva r) {
                if (r == null) throw new IllegalArgumentException("No se puede pasar una reserva nula.");
		
                LocalDate mesPosterior = LocalDate.now().plusMonths(1);
		if (r.getPermanencia().getDia().isBefore(LocalDate.of(mesPosterior.getYear(), mesPosterior.getMonth(), 1)))
			return false;
		return true;
                
                //.isBefore sacado de https://www.tutorialspoint.com/javatime/javatime_localdate_isbefore.htm
                //si devuelve FALSE la reserva pasada al método no es anterior al mes siguiente por lo que será el mismo mes o anterior
                //en cambio si devuelve TRUE la reserva pasada al método es posterior al mes siguiente
                //Eso es lo que he querido hacern no sé si esto es exactamente lo que se vio en clase ni si estará bien pero pasa test
}
    
    
    private float getPuntosGastadosReserva(Reserva r) {
                if (r == null) throw new IllegalArgumentException("No se puede pasar una reserva nula.");
		float puntos = 0;
		List<Reserva> prof = getReservasProfesorMes(r.getProfesor(), r.getPermanencia().getDia());
		for (Reserva reserva : prof) {
			puntos = puntos + reserva.getPuntos();
		}
		return puntos + r.getPuntos();
	}

	
    private List<Reserva> getReservasProfesorMes(Profesor p, LocalDate d) {
                if (p == null) throw new IllegalArgumentException("No se puede pasar un profesor nulo.");
                if (d == null) throw new IllegalArgumentException("No se puede pasar una fecha nula.");
            
		List<Reserva> reservasProfesor = new ArrayList<>();
		for (Reserva reserva : coleccionReservas) {
                    
			if (reserva.getProfesor().equals(p) &&
                            reserva.getPermanencia().getDia().getMonthValue() == d.getMonthValue()&&
                            reserva.getPermanencia().getDia().getYear() == d.getYear())
                        {
				reservasProfesor.add(new Reserva(reserva));
			}
		}
		return reservasProfesor;
	}

	private Reserva getReservaDia(LocalDate dia) {
		if (dia == null) throw new NullPointerException("No se puede buscar una reserva para un día nulo.");
		
		
		for (Reserva reserva : coleccionReservas) {
			if (reserva.getPermanencia().getDia().equals(dia)) {
			 return new Reserva(reserva);}
                }
                
                return null;
		
		
}
	

	public Reserva buscar(Reserva r) {
	int indice = coleccionReservas.indexOf(r);	
        if (indice != -1) {
			return new Reserva(coleccionReservas.get(indice));
		} else {
			return null;
		}
        }
	
	public void borrar(Reserva r) throws OperationNotSupportedException {
		if (r == null) {
			throw new IllegalArgumentException("No se puede anular una reserva nula.");
		}
		if (!coleccionReservas.remove(r)) {
			throw new OperationNotSupportedException("La reserva a anular no existe.");
		}
	}


	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Reserva reservanew : coleccionReservas) {
                      representacion.add(reservanew.toString());
                }
		return representacion;
	}
    
        public List<Reserva> getReservasProfesor(Profesor p) throws IllegalArgumentException{
            
            if(p==null) throw new IllegalArgumentException("No se puede pasar un profesor nulo .");

		List<Reserva> reservasDelProfesor = new ArrayList<>();

		for(Reserva reservanew: coleccionReservas) {

				if(reservanew.getProfesor().equals(p)){
				reservasDelProfesor.add(new Reserva(reservanew));}
		}
		return reservasDelProfesor;
            
        } 
        
        
        public List<Reserva> getReservasAula(Aula a) {
            
            if(a==null) throw new IllegalArgumentException("No se puede pasar un aula nula .");
	
            List<Reserva> reservasDelAula = new ArrayList<>();

		for(Reserva reservanew: coleccionReservas) {

				if(reservanew.getAula().equals(a)){
				reservasDelAula.add(new Reserva(reservanew));}
		}
		return reservasDelAula;
            
        }  
        
        public List<Reserva> getReservasPermanencia(Permanencia p) throws IllegalArgumentException{
            
            if(p==null) throw new IllegalArgumentException("No se puede pasar una permanencia nula .");

	List<Reserva> reservasP = new ArrayList<>();

		for(Reserva reservanew: coleccionReservas) {

				if(reservanew.getPermanencia().equals(p)){
				reservasP.add(new Reserva(reservanew));}
		}
		return reservasP;
            
        } 
        
        
    public boolean consultarDisponibilidad(Aula a,Permanencia p) throws IllegalArgumentException{
        if (a==null) throw new IllegalArgumentException("No se puede consultar la disponibilidad de un aula nula.");
        if (p==null) throw new IllegalArgumentException("No se puede consultar la disponibilidad de una permanencia nula.");
        
        boolean disponible=true;

		for(Reserva reservanew:coleccionReservas) {

			if(
                           reservanew.getPermanencia().equals(p) &&
                           reservanew.getAula().equals(a)
                           ) {
                            
				disponible=false;
                                return disponible;
                              }  
			}
                return disponible;

        }       
    public void leer() throws FileNotFoundException, IOException {
		File ficheroReservas = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroReservas))) {
			Reserva reserva = null;
			do {
				reserva = (Reserva) entrada.readObject();
				insertar(reserva);
			} while (reserva != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido encontrar la clase para leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el fichero de reservas.");
		} catch (EOFException e) {
			System.out.println("El fichero de reservas ha sido leido.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void escribir() {
		File ficheroReservas = new File(NOMBRE_FICHERO_RESERVAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroReservas))) {
			for (Reserva reserva : coleccionReservas) {
				salida.writeObject(reserva);}	
			System.out.println("Fichero de reservas ha sido escrito");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede crear el fichero de reservas.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
    
}

