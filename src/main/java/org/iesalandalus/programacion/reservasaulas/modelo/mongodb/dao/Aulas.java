
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
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.mongodb.utilidades.MongoDB;

/**
 *
 * @author quique
 */
public class Aulas {
	private MongoCollection<Document> coleccionAulas;
        private static final String COLECCION = "aulas";
    
    
    public Aulas(){
	coleccionAulas = MongoDB.getBD().getCollection(COLECCION);

    }
    public List<Aula> getAulas() {
        List<Aula> aulas=new ArrayList();
        for(Document documentoAula:coleccionAulas.find())
        {
            aulas.add(MongoDB.obtenerAulaDesdeDocumento(documentoAula));
        }
		return aulas;
	}

    public int getNumAulas() {
        return (int)coleccionAulas.countDocuments();
    }
    

    public void insertar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede insertar un aula nula.");
}
		if (buscar(aula)!=null ) {
			throw new OperationNotSupportedException("El aula ya existe.");
		} else {
			coleccionAulas.insertOne(MongoDB.obtenerDocumentoDesdeAula(aula));
		}
	
    }
	
	public Aula buscar(Aula aula) {
            
	Document documentoAula=coleccionAulas.find().filter(eq(MongoDB.NOMBRE,aula.getNombre())).first();
        return MongoDB.obtenerAulaDesdeDocumento(documentoAula);
        
        }
	
        
	public void borrar(Aula aula) throws OperationNotSupportedException {
		if (aula == null) {
			throw new IllegalArgumentException("No se puede borrar un aula nula.");
	}
		if (buscar(aula)!=null) {
                 coleccionAulas.deleteOne(eq(MongoDB.NOMBRE,aula.getNombre()));
                }
                else{
			throw new OperationNotSupportedException("El aula a borrar no existe.");
		}
	}
	
	
	public List<String> representar() {
		List<String> representacion = new ArrayList<>();
		for (Aula aulanew : getAulas()) {
                      representacion.add(aulanew.toString());
                }
		return representacion;
	}
           
    
    
}
