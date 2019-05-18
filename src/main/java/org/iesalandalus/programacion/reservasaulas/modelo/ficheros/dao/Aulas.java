
package org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;

/**
 *
 * @author quique
 */
public class Aulas {
	private List<Aula> coleccionAulas;
        private static final String NOMBRE_FICHERO_AULAS = ".\\ficheros\\aulas.dat";
    
    
    public Aulas(){
	coleccionAulas = new ArrayList<>();

    }
    public Aulas(Aulas a){
        setAulas(a);
        
    }
    
    private void setAulas (Aulas a){
        if (a == null) throw new IllegalArgumentException("No se pueden copiar aulas nulas.");
        coleccionAulas = copiaProfundaAulas(a.coleccionAulas);
    }
    
    private List<Aula> copiaProfundaAulas(List<Aula> a) {
		List<Aula> otrasAulas = new ArrayList<>();
                for (Aula aulanew : a){
                otrasAulas.add(new Aula(aulanew));
		}
		return otrasAulas;
	}
    
    public List<Aula> getAulas() {
		return copiaProfundaAulas(coleccionAulas);
	}

    public int getNumAulas() {
        return coleccionAulas.size();
    }
    

    public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
}
		if (coleccionAulas.contains(aula)) {
			throw new OperationNotSupportedException("El aula ya existe.");
		} else {
			coleccionAulas.add(new Aula(aula));
		}
	
    }
	
	public Aula buscar(Aula aula) {
	int indice = coleccionAulas.indexOf(aula);
		if (indice != -1) {
			return new Aula(coleccionAulas.get(indice));
		} else {
			return null;
		}
        }
	
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
	}
		if (!coleccionAulas.remove(aula)) {
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}
	
	
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Aula aulanew : coleccionAulas) {
                      representacion.add(aulanew.toString());
                }
		return representacion;
	}
        public void leer() throws FileNotFoundException, IOException {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroAulas))) {
			Aula aula = null;
			do {
				aula = (Aula) entrada.readObject();
				insertar(aula);
			} while (aula != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido encontrar el aula para leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el fichero de aulas.");
		} catch (EOFException e) {
			System.out.println("El fichero de aulas ha sido leido.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	public void escribir() {
		File ficheroAulas = new File(NOMBRE_FICHERO_AULAS);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroAulas))){
			for (Aula aula : coleccionAulas) {
				salida.writeObject(aula);
			}
			System.out.println("Fichero de aulas ha sido escrito");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede crear el fichero de aulas.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	}
    
    
    
}
