
package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.controladoresVistas;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.naming.OperationNotSupportedException;
import org.iesalandalus.programacion.reservasaulas.controlador.IControladorReservasAulas;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Aula;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Profesor;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.Reserva;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Permanencia;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorHora;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.PermanenciaPorTramo;
import org.iesalandalus.programacion.reservasaulas.modelo.dominio.permanencia.Tramo;
import org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades.Dialogos;


public class ControladorAulas implements Initializable {
            private IControladorReservasAulas controladorMVC;
           
            
    
    @FXML        
    private ListView<String> lvAulas;
    @FXML  
    private ListView<String> lvProfesores;
    @FXML  
    private ListView<String> lvReservas;
   
     @FXML
    private TextArea tanombre;
      @FXML
    private TextArea tapuestos;
      @FXML
    private TextArea tanombreP;
      @FXML
    private TextField tacorreo;
      @FXML
    private TextArea tatelefono;
    @FXML
    private Button btAceptar;
    @FXML
    private Button realizarReserva;
    @FXML
    private ComboBox<String> cbAula;
    @FXML
    private ComboBox<String> cbProfesor;
    @FXML
    private RadioButton rbHora;
    @FXML
    private RadioButton rbTramo;
    @FXML
    private RadioButton rbtramoM;
    @FXML
    private RadioButton rbtramoT;
    @FXML
    private Label tramo;
    @FXML
    private Label lhora;
    @FXML
    private TextField tfhora;

    @FXML
    private DatePicker fecha;
    
    private ToggleGroup tgTipoPermanencia;
    private ToggleGroup tgTipoTramo;

  ObservableList<Aula> aulas=FXCollections.observableArrayList();
  private ObservableList<String> aulasNombre;
  ObservableList<Profesor> profesores=FXCollections.observableArrayList();
  private ObservableList<String> profesoresNombre;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializaCalendario();
        tipoPermanencia(); 
        //cbAula.setItems(aulas);
        //cbProfesor.setItems(profesores);
	//	cbTipo.valueProperty().addListener((ob, ov, nv) -> actualizar(cbTipo.getValue()));
    }   
    
      public void setControladorMVC(IControladorReservasAulas controlador) 
    {           
        controladorMVC = controlador;        
    }
      
      
   private void inicializaCalendario()
    {
        final Callback<DatePicker, DateCell> dayCellFactory = 
            new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                           
                            if (item.isBefore(
                                    LocalDate.now().plusDays(1))
                                ) {
                                    setDisable(true);
                                    setStyle("-fx-background-color: orange;");
                            }   
                    }
                };
            }
        };
        fecha.setDayCellFactory(dayCellFactory);
    }
      private void tipoPermanencia()
    {
        tgTipoPermanencia=new ToggleGroup();
        tgTipoTramo = new ToggleGroup();

        rbHora.setToggleGroup(tgTipoPermanencia);
        rbTramo.setToggleGroup(tgTipoPermanencia);
        
        rbtramoM.setToggleGroup(tgTipoTramo);
        rbtramoT.setToggleGroup(tgTipoTramo);
        //rbTramo.setSelected(true);
        tgTipoPermanencia.selectedToggleProperty().addListener((observable, oldValue, newValue) -> muestraPermanencia());
    }
    
    private void muestraPermanencia()
    {
        RadioButton seleccionado = (RadioButton)tgTipoPermanencia.getSelectedToggle();
        
        if (seleccionado == rbTramo)
        {
            tramo.setVisible(true);
            rbtramoT.setVisible(true);
            rbtramoM.setVisible(true);
            tfhora.setVisible(false);
            lhora.setVisible(false);
        }
        if (seleccionado == rbHora)
        {
            tramo.setVisible(false);
            rbtramoT.setVisible(false);
            rbtramoM.setVisible(false);
            tfhora.setVisible(true);
            lhora.setVisible(true);
            
            
        }
            
        
    }

        private ObservableList<String> getAulasNombre(List<Aula> listaAulas){
		aulasNombre = FXCollections.observableArrayList();
		if (!listaAulas.isEmpty()) {
			for (Aula a : listaAulas) {
				aulasNombre.add(a.getNombre());
			}
			return aulasNombre;
		} else {
			aulasNombre.add("No hay aulas");
			return aulasNombre;
		} 
        }

        private ObservableList<String> getProfesoresNombre(List<Profesor> listaProfesores){
		profesoresNombre = FXCollections.observableArrayList();
		if (!listaProfesores.isEmpty()) {
			for (Profesor profesor : listaProfesores) {
				profesoresNombre.add(profesor.getNombre());
			}
			return profesoresNombre;
		} else {
			profesoresNombre.add("No hay profesores");
			return profesoresNombre;
		} 
           
        }
        
        
        public void nombresAP() {

            aulas.setAll(controladorMVC.getAulas());
            profesores.setAll(controladorMVC.getProfesores());
            cbAula.setItems(FXCollections.observableArrayList(getAulasNombre(aulas)));
            cbProfesor.setItems(FXCollections.observableArrayList(getProfesoresNombre(profesores)));
}
        
        
        public void getReservas()
    {
        ObservableList<String> reservas=FXCollections.observableArrayList();
        
        for (String cadena:controladorMVC.representarReservas())
           reservas.add(cadena);
        
        lvReservas.setItems(reservas);
    }
        
        
        
        
           public void getAulas()
    {
        ObservableList<String> aulas=FXCollections.observableArrayList();
        
        for (String cadena:controladorMVC.representarAulas())
           aulas.add(cadena);
        
        lvAulas.setItems(aulas);
    }
           
           
           
           public void getProfesores()
    {
        ObservableList<String> profesores=FXCollections.observableArrayList();
        
        for (String cadena:controladorMVC.representarProfesores())
           profesores.add(cadena);
        
        lvProfesores.setItems(profesores);
    }
           
           
           
           
        
        @FXML private void anadirA(ActionEvent event) throws OperationNotSupportedException
    {    
        try 
        {
             Aula auli=new Aula(tanombre.getText(),Integer.parseInt(tapuestos.getText()));
             
             controladorMVC.insertarAula(auli);
            // System.out.println(auli);
             Stage stage = (Stage) btAceptar.getScene().getWindow();
				stage.close();
              Dialogos.mostrarDialogoInformacion("Nueva Aula", "Aula creada correctamente",null);
             
        }
             catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e)            
            {
                Dialogos.mostrarDialogoError("Aula", e.getMessage());
            } 

    }
        
        
        
        
        
        @FXML private void eliminarA(ActionEvent event) throws OperationNotSupportedException
    {    
         try 
        {
        
    Aula auli=new Aula(tanombre.getText(),Integer.parseInt(tapuestos.getText()));
             controladorMVC.borrarAula(auli);
             //System.out.println(auli);
             Stage stage = (Stage) btAceptar.getScene().getWindow();
		stage.close();		
             Dialogos.mostrarDialogoInformacion("Borrar Aula", "Aula eliminada correctamente");
             
     }
             catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e)            
            {
                Dialogos.mostrarDialogoError("Aula", e.getMessage());
            } 
    }
        
        
        
        
        @FXML private void anadirP(ActionEvent event) throws OperationNotSupportedException
    {    
        try 
        {   
            
            if ("".equals(tatelefono.getText()) || tatelefono.getText()==null){
                             Profesor p=new Profesor(tanombreP.getText(),tacorreo.getText());
                             controladorMVC.insertarProfesor(p);
            }
            else{
             Profesor p=new Profesor(tanombreP.getText(),tacorreo.getText(),tatelefono.getText());
             controladorMVC.insertarProfesor(p);
            }
             
            // System.out.println(p);
             Stage stage = (Stage) btAceptar.getScene().getWindow();
		stage.close();
             Dialogos.mostrarDialogoInformacion("Nuevo profesor", "Profesor creado correctamente");
        }
             catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e)            
            {
                Dialogos.mostrarDialogoError("Profesor", e.getMessage());
            } 

    }
        
        
        
        
        @FXML private void eliminarP(ActionEvent event) throws OperationNotSupportedException
    {    
        try 
        {
            if ("".equals(tatelefono.getText()) || tatelefono.getText()==null){
                             Profesor p=new Profesor(tanombreP.getText(),tacorreo.getText());
                             controladorMVC.borrarProfesor(p);
            }
            else{
                 Profesor p=new Profesor(tanombreP.getText(),tacorreo.getText(),tatelefono.getText());
                    controladorMVC.borrarProfesor(p);
            }             
                Stage stage = (Stage) realizarReserva.getScene().getWindow();
		stage.close();
                Dialogos.mostrarDialogoInformacion("Nuevo profesor", "Profesor creado correctamente");
        }
        catch(OperationNotSupportedException | IllegalArgumentException | NullPointerException e)            
            {
                Dialogos.mostrarDialogoError("Profesor", e.getMessage());
            } 

    }
        
        
        
        
        	@FXML
	void realizarReserva(ActionEvent event) throws OperationNotSupportedException {
		try {
                      RadioButton seleccionado = (RadioButton)tgTipoPermanencia.getSelectedToggle();
                      RadioButton selec = (RadioButton)tgTipoTramo.getSelectedToggle();

                      Aula a = controladorMVC.buscarAula(new Aula(cbAula.getValue(), 99));
                      Profesor p = controladorMVC.buscarProfesor(new Profesor(cbProfesor.getValue(), "tiki@tiki.tik"));
                      Permanencia preserva;
                        //Aula aula=new Aula(cbAula.);
                       //System.out.println(cbAula.getValue());

			if(seleccionado==rbHora) {
                            
				preserva = new PermanenciaPorHora(fecha.getValue(), tfhora.getText());
			} else {
				if(selec==rbtramoM) {
					preserva = new PermanenciaPorTramo(fecha.getValue(), Tramo.MANANA);
				} else {
					preserva = new PermanenciaPorTramo(fecha.getValue(), Tramo.TARDE);
					}
				}
                        
				
                        if(controladorMVC.consultarDisponibilidad(a, preserva)) {
                            
				controladorMVC.realizarReserva(new Reserva(p, a, preserva));
                                Stage stage = (Stage) btAceptar.getScene().getWindow();
                                stage.close();
                                Dialogos.mostrarDialogoInformacion("¡Nueva reserva!","La reserva se ha realizado con éxito");
                                        
				} else {
					Dialogos.mostrarDialogoAdvertencia("Aula ocupada", "Prueba otra aula o fecha...");
				}
                
			
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage());
		}


        }  
        
        @FXML
	void anularReserva(ActionEvent event) throws OperationNotSupportedException {
		try {
                            RadioButton seleccionado = (RadioButton)tgTipoPermanencia.getSelectedToggle();
                            RadioButton selec = (RadioButton)tgTipoTramo.getSelectedToggle();

                        Aula a = controladorMVC.buscarAula(new Aula(cbAula.getValue(), 99));
			Profesor p = controladorMVC.buscarProfesor(new Profesor(cbProfesor.getValue(), "tiki@tiki.tik"));
			Permanencia preserva;

                      // System.out.println(cbAula.getValue());

			if(seleccionado==rbHora) {
					preserva = new PermanenciaPorHora(fecha.getValue(), tfhora.getText());
				} else {
					if(selec==rbtramoM) {
						preserva = new PermanenciaPorTramo(fecha.getValue(), Tramo.MANANA);
					} else {
						preserva = new PermanenciaPorTramo(fecha.getValue(), Tramo.TARDE);
					}
				}
				if(!controladorMVC.consultarDisponibilidad(a, preserva)) {
					controladorMVC.anularReserva(new Reserva(p, a, preserva));
                                        Stage stage = (Stage) btAceptar.getScene().getWindow();
                                        stage.close();

					Dialogos.mostrarDialogoInformacion("Reserva anulada","La reserva se ha anulado");
                                        
				} else {
					Dialogos.mostrarDialogoAdvertencia("Error", "No existe tal reserva");
				}
                
			
		} catch (Exception e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage());
		}


        } 
    
}