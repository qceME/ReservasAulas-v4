/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
/**
 *
 * @author quique
 */
public abstract class Permanencia implements Serializable {
            
    protected LocalDate dia;
    protected static final DateTimeFormatter FORMATO_DIA=DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    
     protected Permanencia (){
           
    }
     protected Permanencia(LocalDate d){
        
         setDia(d);     
    }
       
     protected Permanencia(String d){
        
         setDia(d);     
    }
    
    public LocalDate getDia() {
        return dia;
    }

    protected void setDia(LocalDate dia) {
        if (dia==null) throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");

        this.dia = dia;
    }
       protected void setDia(String dia) {
        if (dia==null) throw new IllegalArgumentException("El día de una permanencia no puede ser nulo.");

         try { 
             this.dia = LocalDate.parse(dia,FORMATO_DIA);

            } catch (DateTimeParseException e) {  throw new IllegalArgumentException("El formato del día de la permanencia no es correcto."); }
    }
       
       
       public abstract int getPuntos();
       
        
        @Override
        public abstract String toString() ;

        @Override
        public abstract int hashCode() ;

    @Override
    public abstract boolean equals(Object obj) ;
       
   
    
    
}
