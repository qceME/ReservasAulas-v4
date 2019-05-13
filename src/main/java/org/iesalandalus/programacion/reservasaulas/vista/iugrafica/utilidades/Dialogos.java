package org.iesalandalus.programacion.reservasaulas.vista.iugrafica.utilidades;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Dialogos {
	
	public static void mostrarDialogoError(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.ERROR);
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId("btAceptar");
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if (propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if (propietario != null)
			propietario.close();
	}
	
	public static void mostrarDialogoError(String titulo, String contenido) {
		Dialogos.mostrarDialogoError(titulo, contenido, null);
	}
	
	public static void mostrarDialogoInformacion(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.INFORMATION);
                                Stage stage = (Stage) dialogo.getDialogPane().getScene().getWindow();

		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId("btAceptar");
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if (propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if (propietario != null)
			propietario.close();
	}
	
	public static void mostrarDialogoInformacion(String titulo, String contenido) {
		Dialogos.mostrarDialogoInformacion(titulo, contenido, null);
	}
	
	public static void mostrarDialogoAdvertencia(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.WARNING);
                                                Stage stage = (Stage) dialogo.getDialogPane().getScene().getWindow();

		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId("btAceptar");
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if (propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}
		dialogo.showAndWait();
		if (propietario != null)
			propietario.close();
	}
	
	public static void mostrarDialogoAdvertencia(String titulo, String contenido) {
		Dialogos.mostrarDialogoAdvertencia(titulo, contenido, null);
	}
	
	public static String mostrarDialogoTexto(String titulo, String contenido) {
		TextInputDialog dialogo = new TextInputDialog();
                                                Stage stage = (Stage) dialogo.getDialogPane().getScene().getWindow();

		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId("btAceptar");
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.CANCEL)).setId("btCancelar");
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);

		Optional<String> respuesta = dialogo.showAndWait();
		return (respuesta.isPresent() ? respuesta.get() : null);
	}
	
	public static boolean mostrarDialogoConfirmacion(String titulo, String contenido, Stage propietario) {
		Alert dialogo = new Alert(AlertType.CONFIRMATION);
                Stage stage = (Stage) dialogo.getDialogPane().getScene().getWindow();

		((Button) dialogo.getDialogPane().lookupButton(ButtonType.OK)).setId("btAceptar");
		((Button) dialogo.getDialogPane().lookupButton(ButtonType.CANCEL)).setId("btCancelar");
		dialogo.setTitle(titulo);
		dialogo.setHeaderText(null);
		dialogo.setContentText(contenido);
		if (propietario != null) {
			dialogo.initModality(Modality.APPLICATION_MODAL);
			dialogo.initOwner(propietario);
		}

		Optional<ButtonType> respuesta = dialogo.showAndWait();
		return (respuesta.get() == ButtonType.OK ? true : false);
	}
	
}
