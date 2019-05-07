/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dao;

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
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;

/**
 *
 * @author quique
 */
public class Profesores {
    
    private List<Profesor> coleccionProfesores;
    private static final String NOMBRE_FICHERO_PROFESORES = ".\\ficheros\\profesores.dat";
    
    
    public Profesores(){
        coleccionProfesores=new ArrayList<>();
        
    }
    public Profesores(Profesores a){
        setProfesores(a);
        
    }
    
    private void setProfesores (Profesores p){
        if (p == null) throw new IllegalArgumentException("No se pueden copiar profesores nulos.");
        coleccionProfesores = copiaProfundaProfesores(p.coleccionProfesores);
    }
    
    private List<Profesor> copiaProfundaProfesores(List<Profesor> p) {
		List<Profesor> otrosProfes = new ArrayList<>();
                for (Profesor profenew : p){
                otrosProfes.add(new Profesor(profenew));
		}
		return otrosProfes;
	}
    
    public List<Profesor> getProfesores() {
		return copiaProfundaProfesores(coleccionProfesores);
	}

    public int getNumProfesores() {
        return coleccionProfesores.size();
    }
    

    public void insertar(Profesor p) throws OperationNotSupportedException {
		if (p == null) {
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
}
		if (coleccionProfesores.contains(p)) {
			throw new OperationNotSupportedException("El profesor ya existe.");
		} else {
			coleccionProfesores.add(new Profesor(p));
		}
	
    }

	
	public Profesor buscar(Profesor p) {
	int indice = coleccionProfesores.indexOf(p);
		if (indice != -1) {
			return new Profesor(coleccionProfesores.get(indice));
		} else {
			return null;
		}
        }
	


	public void borrar(Profesor p) throws OperationNotSupportedException {
		if (p == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
	}
		if (!coleccionProfesores.remove(p)) {
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
		}
	}
	
	
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Profesor profenew : coleccionProfesores) {
                      representacion.add(profenew.toString());
                }
		return representacion;
	}
        public void leer() {
		File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
		try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(ficheroProfesores))) {
			Profesor profesor = null;
			do {
				profesor = (Profesor) entrada.readObject();
				insertar(profesor);
			} while (profesor != null);
		} catch (ClassNotFoundException e) {
			System.out.println("No se ha podido encontrar la clase para leer.");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede abrir el fichero de profesores.");
		} catch (EOFException e) {
			System.out.println("El fichero de profesores ha sido leido.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida.");
		} catch (OperationNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}

	public void escribir() {
		File ficheroProfesores = new File(NOMBRE_FICHERO_PROFESORES);
		try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(ficheroProfesores))) {
			for (Profesor profesor : coleccionProfesores) {
				salida.writeObject(profesor);
			}
			System.out.println("Fichero de profesores ha sido escrito");
		} catch (FileNotFoundException e) {
			System.out.println("No se puede crear el fichero de profesores.");
		} catch (IOException e) {
			System.out.println("Error inesperado de Entrada/Salida");
		}
	
    
    
    
        }
        
}
