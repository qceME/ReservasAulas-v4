package org.iesalandalus.programacion.reservasaulas.vista.iugrafica;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.IVistaReservasAulas;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresVistas.PrincipalController;


public class VistaReservasAulas extends Application implements IVistaReservasAulas {
	
	private IControladorReservasAulas controladorMVC;
        private static VistaReservasAulas instancia=null;

        public VistaReservasAulas()
        {
            //System.out.println("IUGVEntanas: Constructor llamado");
            if (instancia != null) 
            {
                controladorMVC = instancia.controladorMVC;
            } 
            else 
            {
                instancia = this;
            }
        }      

        
	@Override
	public void start(Stage escenarioPrincipal) 
        {
            try 
            {                       
               FXMLLoader loader=new FXMLLoader(getClass().getResource("vistas/principal.fxml"));			
                System.out.println("Llamada a load");
               Parent raiz=loader.load();                                
                
                System.out.println("Antes de setControlador");
                PrincipalController controlador=loader.getController();
                controlador.setControladorMVC(controladorMVC);
               
                Scene escena = new Scene(raiz);
                escenarioPrincipal.setOnCloseRequest(e -> confirmarSalida(escenarioPrincipal, e));

		escenarioPrincipal.setTitle("Al-Ándalus");
		escenarioPrincipal.setScene(escena);
		escenarioPrincipal.setResizable(false);
		escenarioPrincipal.show();
            } 
            catch(Exception e) 
            {
                e.printStackTrace();
            }
        }

	public void confirmarSalida(Stage escenarioPrincipal, WindowEvent e) {
		if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Estás seguro de que quieres salir de la aplicación?", escenarioPrincipal)) {
			controladorMVC.salir();
			escenarioPrincipal.close();
		}
		else
			e.consume();	
	}



	
	public IControladorReservasAulas getControladorMVC() {
		return controladorMVC;
	}       
       
       
	@Override
	public void comenzar() {            
		launch(this.getClass());
	}

    @Override
    public void anularReserva() throws OperationNotSupportedException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setControlador(IControladorReservasAulas controlador) {
    this.controladorMVC = controlador; 
    }
    

    @Override
    public void borrarProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscarAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void buscarProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void consultarDisponibilidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertarProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarAulas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarProfesores() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservasAula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservasPermanencia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void listarReservasProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void realizarReserva() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void salir() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
