
package org.iesalandalus.programacion.reservasaulas.vista.iutextual;

import java.time.format.DateTimeParseException;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.ModeloReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 *
 * @author quique
 */

public class VistaReservasAulas implements IVistaReservasAulas {
    
    private static final String NOMBRE_VALIDO="[a-zA-Z]+";
    private static final String CORREO_VALIDO="arr@gmail.com";
    private static final String ERROR = "ERROR: ";
	
	private IControladorReservasAulas controlador;

	public VistaReservasAulas() {
		
		Opcion.setVista(this);
	}
        public void setControlador(IControladorReservasAulas controlador) {
		this.controlador = controlador;
}

    @Override
	public void comenzar() {
		int ordinalOpcion;
		do {
			Consola.mostrarMenu();
			ordinalOpcion = Consola.elegirOpcion();
			Opcion opcion = Opcion.getOpcionSegunOrdinal(ordinalOpcion);
			opcion.ejecutar();
		} while (ordinalOpcion != Opcion.SALIR.ordinal());
	}
	
    @Override
	public void salir() {
                controlador.salir();
		System.out.println("Hasta luego Lucas!!!");
	}
	
    @Override
	public void insertarAula() {
		Consola.mostrarCabecera("Insertar aula");
                
               
                
                try {
			Aula aula = Consola.leerAula();
			controlador.insertarAula(aula);
                        System.out.println("Se agregó el Aula correctamente. " + aula);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			System.out.println("Aula no insertada.");
		}
	}

	
    @Override
	public void borrarAula() {
		Consola.mostrarCabecera("Borrar aula");
		try {
                    Aula aula = Consola.leerAula();
			controlador.borrarAula(aula);
                        System.out.println("Se borro el Aula correctamente. " + aula);
		} catch (Exception e) {
			System.out.println(ERROR + e.getMessage());
			System.out.println("No se pudo eliminar el aula.");
		}
	}

	
    @Override
	public void buscarAula() {
		Consola.mostrarCabecera("Buscar aula");
                Aula aBuscar = Consola.leerAula();
		try {
			
			Aula aulaRecibida = controlador.buscarAula(aBuscar);
			if (aulaRecibida != null) {
				System.out.println("Se encontró el Aula: " );
			} else {
				System.out.println("No se encontro el Aula. ");
}
		} catch (IllegalArgumentException e) {
			System.out.println(ERROR + e.getMessage());
		}

	}

	
    @Override
	public void listarAulas() {
		Consola.mostrarCabecera("Listar aulas");
		
                List<String> aulas = controlador.representarAulas();
		if (aulas.size() == 0) System.out.println("No hay aulas.");
		
                
                for (String listaAulas : aulas)
			System.out.println(listaAulas);
	}

	
    @Override
	public void insertarProfesor() {
	Consola.mostrarCabecera("Insertar Profesor"); 
        
		try {
		Profesor profesor = Consola.leerProfesor();
		controlador.insertarProfesor(profesor);
		System.out.println("Se inserto el Profesor correctamente. " + profesor);
                        
		}catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
                      
		}   catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}

	
    @Override
	public void borrarProfesor() {
		Consola.mostrarCabecera("Borrar Profesor");
		try {
		String n = Consola.leerNombreProfesor();
		Profesor p = new Profesor(n, CORREO_VALIDO);
		controlador.borrarProfesor(p);
			System.out.println("Se borro correctamente el profesor . " + "[nombre=" + n + "]");
                    } catch (OperationNotSupportedException e) {
                    
			System.out.println(ERROR + e.getMessage());
                        
                    } catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}

	
    @Override
	public void buscarProfesor() {
	Consola.mostrarCabecera("Buscar Profesor");
		try {
			String n = Consola.leerNombreProfesor();
			Profesor p = new Profesor(n, CORREO_VALIDO);
			Profesor p1 = controlador.buscarProfesor(p);
			if (p1 == null) {
				System.out.println("Profesor no encontrado. " );
			} else {
				System.out.println("Profesor encontrado. " );
			}
		} catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}

	
    @Override
	public void listarProfesores() {
		Consola.mostrarCabecera("Listar profesores");
		
                List<String> profesores = controlador.representarProfesores();
		if (profesores.size() == 0) System.out.println("No hay profesores.");
		
                
                for (String listar : profesores)
			System.out.println(listar);
	}

	
    @Override
	public void realizarReserva() {
	Consola.mostrarCabecera("Realizar Reserva");
        
        
		try {
		String n = Consola.leerNombreProfesor();
		Profesor p = controlador.buscarProfesor(new Profesor(n, CORREO_VALIDO));
			
                if (p == null) {
				System.out.println("El profesor deberá de estar registrado... ") ;
				this.comenzar();
			}
                
			Reserva reserva = leerReserva(p);
                        
                        
			if (reserva == null) {
                            
			} else {
				controlador.realizarReserva(reserva);
				System.out.println("Reserva hecha");
			}
		} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
		} catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}

	
	private Reserva leerReserva(Profesor profesor) {
		Reserva reserva = null;
		try {
			Aula aula = Consola.leerAula();
			Aula aulaSalida = controlador.buscarAula(aula);
			if (aulaSalida == null) {
				System.out.println("El Aula debe estar registrada. " + aula);
			} else {
				Permanencia permanencia = Consola.leerPermanencia();
				reserva = new Reserva(profesor, aulaSalida, permanencia);
			}
		} catch (DateTimeParseException e) {
			System.out.println(ERROR + "Fecha incorrecta.");
		}
		return reserva;
}

    @Override
	public void anularReserva() throws OperationNotSupportedException {
		Consola.mostrarCabecera("Anular Reserva");
                    try {
                    
		Reserva reserva = leerReserva(new Profesor("pwpw", "a@a.a"));
		controlador.anularReserva(reserva);
               
		System.out.println("Rserva anulada. " + reserva);
                
           
                	} catch (OperationNotSupportedException e) {
			System.out.println(ERROR + e.getMessage());
                        } catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}

	
    @Override
	public void listarReservas() {
		Consola.mostrarCabecera("Listar reservas");
                
		List<String> reservas = controlador.representarReservas();
		if (reservas.size() == 0) System.out.println("No existen reservas.");
                
                    for (String listar : reservas) System.out.println(listar);
	}

	
    @Override
	public void listarReservasAula() {
		Consola.mostrarCabecera("Lista de reservas por Aula");
                
		try {
		Aula aula = Consola.leerAula();
		List<Reserva> r = controlador.getReservasAula(aula);
                
                
			if (r.size() < 1) {
				System.out.println("No hay reservas: " + aula);
                                
                                
			} else {
				Consola.mostrarCabecera("Lista de reservas por Aula: " + aula);
				
				for (Reserva reserva : r) {
                                    
					System.out.println(reserva);
                                        
				}
			}
		} catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}
	
    @Override
	public void listarReservasProfesor() {
	Consola.mostrarCabecera("Lista de reservas por Profesor");
        
		try {
                    
		Profesor profesor = new Profesor(Consola.leerNombreProfesor(), "a@a.a");
		List<Reserva> reservas = controlador.getReservasProfesor(profesor);
                
			if (reservas.size() < 1) {
				System.out.println("No existen reservas: ");
			} else {
				Consola.mostrarCabecera("Lista de reservas por Profesor: ");
				
				for (Reserva reserva : reservas) {
					System.out.println(reserva);
                                        
				}
			}
		} catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}

	
    @Override
	public void listarReservasPermanencia() {
	Consola.mostrarCabecera("Lista de reservas por Permanencia");
        
		try {
                    
                Permanencia permanencia = Consola.leerPermanencia();

		List<Reserva> reservas = controlador.getReservasPermanencia(permanencia);
			
                if (reservas.size() < 1) {
				System.out.println("No hay reservas " + permanencia);
                                
                                
			} else {
				Consola.mostrarCabecera("Lista de reservas por Permanencia: " + permanencia);
				
				for (Reserva reserva : reservas) {
					System.out.println( reserva);
				}
			}
		} catch (IllegalArgumentException e1) {
			System.out.println(ERROR + e1.getMessage());
		}
}

	
    @Override
	public void consultarDisponibilidad() {
		Consola.mostrarCabecera("Consultar disponibilidad");
                
		try {
			Aula aula = Consola.leerAula();
                        
			if (controlador.buscarAula(aula) != null) {
                            
                            
				Permanencia p = new PermanenciaPorTramo(Consola.leerDia(), Consola.leerTramo());
				boolean disponible = controlador.consultarDisponibilidad(aula, p);
                                
                                
				if (disponible == true) {
					System.out.println("Disponible. ");
				} else {
					System.out.println("No  disponible. ");
				}
                                
			} else {
				System.out.println("No hay reservas.");
			}
		} catch (IllegalArgumentException e1) {
			System.out.println(ERROR);
		} catch (DateTimeParseException e2) {
			System.out.println(ERROR);
		}
	}
}