
package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresVistas;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;


public class PrincipalController implements Initializable {
        private IControladorReservasAulas controladorMVC;

      @FXML
      private Label info;
      
    @FXML
    private void mostrar(javafx.event.ActionEvent event) {
        info.setVisible(true);

        
        
    }
    @FXML
    private void ocultar(javafx.event.ActionEvent event) {
        info.setVisible(false);
    }
   
         @FXML private void vaulas(javafx.event.ActionEvent event)
    {
        try 
        {
            FXMLLoader loader=new FXMLLoader(getClass().getResource("../vistas/reservas_1.fxml"));
            Parent raiz=loader.load();
                    
            ControladorAulas controlador=loader.getController();
            controlador.setControladorMVC(controladorMVC);
            controlador.getAulas();
            controlador.getProfesores();
            controlador.getReservas();
            controlador.nombresAP();

            Scene escena=new Scene(raiz);
            
            Stage escenario=new Stage();
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.setScene(escena);
            escenario.setTitle("Gestión Al-Ándalus");
            escenario.showAndWait();
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

     public void setControladorMVC(IControladorReservasAulas controlador) 
    {           
        controladorMVC = controlador;        
    }
     
   
       
     
     
         @Override
     public void initialize(URL url, ResourceBundle rb) {
        // TODO
                System.out.println("Llamada a inicialice");

    }  

   
    
    
    
    
     
   
    
}
