/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.mongodb.dao;

import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.iesalandalus.programacion.reservasaulas.modelo.ficheros.dao.*;
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
import org.bson.Document;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB;

/**
 *
 * @author quique
 */
public class Profesores {
    
    private MongoCollection<Document> coleccionProfesores;
    private static final String COLECCION = "profesores";
    
    
    public Profesores(){
        coleccionProfesores= MongoDB.getBD().getCollection(COLECCION);
        
    }
   
    
    public List<Profesor> getProfesores() {
		List<Profesor> profesores=new ArrayList();
        for(Document documentoProfesor:coleccionProfesores.find())
        {
            profesores.add(MongoDB.obtenerProfesorDesdeDocumento(documentoProfesor));
        }
		return profesores;
	}
	

    public int getNumProfesores() {
        return (int) coleccionProfesores.countDocuments();
    }
    

    public void insertar(Profesor p) throws OperationNotSupportedException {
		if (p == null) {
			throw new IllegalArgumentException("No se puede insertar un profesor nulo.");
}
		if (buscar(p)!=null) {
			throw new OperationNotSupportedException("El profesor ya existe.");
		} else {
			coleccionProfesores.insertOne(MongoDB.obtenerDocumentoDesdeProfesor(p));
		}
	
    }

	
	public Profesor buscar(Profesor p) {
	Document documentoProfesor=coleccionProfesores.find().filter(eq(MongoDB.NOMBRE,p.getNombre())).first();
        return MongoDB.obtenerProfesorDesdeDocumento(documentoProfesor);
        }
	


	public void borrar(Profesor p) throws OperationNotSupportedException {
		if (p == null) {
			throw new IllegalArgumentException("No se puede borrar un profesor nulo.");
	}
		if (buscar(p)!=null) {
                 coleccionProfesores.deleteOne(eq(MongoDB.NOMBRE,p.getNombre()));
                }
                else{
			throw new OperationNotSupportedException("El profesor a borrar no existe.");
		}
	}
	
	
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Profesor profenew : getProfesores()) {
                      representacion.add(profenew.toString());
                }
		return representacion;
        }
       }
