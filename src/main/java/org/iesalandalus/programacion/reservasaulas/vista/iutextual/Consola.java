
package org.iesalandalus.programacion.reservasaulas.vista.iutextual;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.utilidades.Entrada;

/**
 *
 * @author quique
 */

public class Consola {
    private static final DateTimeFormatter FORMATO_DIA=DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
	private Consola() {
		//Evito que se cree el constructor por defecto
	}
	
	public static void mostrarMenu() {
		mostrarCabecera("Gestión de aulas");
		for (Opcion opcion: Opcion.values()) {
			System.out.println(opcion);
		}
	}
	
	public static void mostrarCabecera(String mensaje) {
		System.out.printf("%n%s%n", mensaje);
		String cadena = "%0" + mensaje.length() + "d%n";
		System.out.println(String.format(cadena, 0).replace("0", "-"));
	}
	
	public static int elegirOpcion() {
		int ordinalOpcion;
		do {
			System.out.println("Elige una opción: ");
			ordinalOpcion = Entrada.entero();
		} while (!Opcion.esOrdinalValido(ordinalOpcion));
		return ordinalOpcion;
	}
	
	public static Aula leerAula() {
          Aula leida = null;
		String nombre = leerNombreAula();
		System.out.println("Introduzca el número de puestos del aula.");
		int puestos = Entrada.entero();
		leida = new Aula(nombre, puestos);
		System.out.println("Aula leída correctamente.");
		return leida;
}
	
	public static String leerNombreAula() {
		String n;
		do {
			System.out.println("Introduce el nombre del aula: ");
			n = Entrada.cadena();
		} while (n.trim().equals(""));
		return n;
	}
        
	public static Profesor leerProfesor() 	{	
                Profesor pl=null;
		do {
		try {
			String n = leerNombreProfesor();
			System.out.println("Por favor, introduce el correo:");
			String c = Entrada.cadena();
			System.out.println("Por favor, introduce el teléfono en el caso de tenerlo:");
			String t = Entrada.cadena();
			if(t.equals(""))
				pl = new Profesor(n, c);
			else
				pl = new Profesor(n, c, t);

                    } catch (IllegalArgumentException e) 
                    {
		
                       System.out.println(e.getMessage());}
                
		} while(pl==null);
		return pl;
	}
        
	public static String leerNombreProfesor() {
		String n;
		do {
			System.out.println("Introduce el nombre del profesor: ");
			n = Entrada.cadena();
		} while (n.trim().equals(""));
		return n;
	}   
        
        public static Tramo leerTramo() {
		int i=0;
                System.out.println("0->"+Tramo.MANANA);
                System.out.println("1->"+Tramo.TARDE);

		do {
			System.out.println("Introduce un tramo (0 ó 1) ");
			i = Entrada.entero();
		} while (i!=0 && i!=1);
		return Tramo.values()[i];
	}   

        public static LocalDate leerDia() {
            
		int d,m,y;
                
                Calendar cal= Calendar.getInstance();
                int year= cal.get(Calendar.YEAR);

		do {
			System.out.println("Introduce el día: ");
			d = Entrada.entero();
		} while (d<=0 || d>31);
                
                do {
			System.out.println("Introduce el mes (1-12): ");
			m = Entrada.entero();
		} while (m<=0 || m>12);
                
                do {
			System.out.println("Introduce el año: ");
			y = Entrada.entero();
		} while (y<year);
                
                LocalDate date=LocalDate.of(y, m, d);
                return date;
		
	}
           public static String leerHora() {
		
		System.out.println("Introduzca la hora en formato hora y minutos");
                String hora = Entrada.cadena();
		return hora;
        }
      public static Permanencia leerPermanencia() {
		if(elegirPermanencia()==1) { return (Permanencia) new PermanenciaPorTramo(leerDia(), leerTramo());
                
		} else { return (Permanencia) new PermanenciaPorHora(leerDia(), leerHora());
		}
}
        public static int elegirPermanencia() {
		int permanencia = 0;
		do {
			System.out.println("Permanencia, ¿Por horas->0 o por tramo ->1?");
                        
			permanencia=Entrada.entero();
                        
		} while (permanencia < 0 || permanencia > 1);
		return permanencia;
} 
}
