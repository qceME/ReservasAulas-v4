/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author quique
 */
public class Profesor implements Serializable{
    private static final String ER_TELEFONO="([69]{1})([0-9]{8})";
    private static final String ER_CORREO="([a-zA-z0-9.-_]{1,})(\\@[a-zA-z]{1,})(\\.[a-z]{1,3})";
    private String nombre;
    private String correo;
    private String telefono;
    
    
    public Profesor (String n, String c, String t){
        setNombre(n);
        setCorreo(c);
        setTelefono(t);
    }
    
        public Profesor (String n, String c){
        setNombre(n);
        setCorreo(c);
    }
    public Profesor (Profesor p){
        if (p==null) throw new IllegalArgumentException("No se puede copiar un profesor nulo.");
        this.nombre=p.getNombre();
        this.correo=p.getCorreo();
        this.telefono=p.getTelefono();
    }

    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        if (nombre==null) throw new IllegalArgumentException("El nombre del profesor no puede ser nulo.");
        if (nombre.equals("")) throw new IllegalArgumentException("El nombre del profesor no puede estar vacío.");
        this.nombre = nombre;
    }

    public String getCorreo() {
        
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo==null) throw new IllegalArgumentException("El correo del profesor no puede ser nulo.");
        else if (correo.matches(ER_CORREO))
                {
                    this.correo = correo;
                }
        else 
                {
                    throw new IllegalArgumentException("El correo del profesor no es válido.");
 
                }
    }

    public String getTelefono() {
        return telefono;
    }
    
    

    public void setTelefono(String telefono) {
        
        if (telefono==null) {this.telefono=null;}
        
        else{
        
        if (telefono.matches(ER_TELEFONO))
                {
                   this.telefono = telefono; 
                }  
        else 
                {
                    throw new IllegalArgumentException("El teléfono del profesor no es válido.");
                }
            }   
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if (telefono==null || telefono==""){
            
            return "[nombre=" + nombre + ", correo=" + correo + "]";
        }
        else
        return "[nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + ']';
    }
    
    
    
}