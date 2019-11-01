package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CreateUserPaneController {

	private PrincipalPaneController principalPane;

	@FXML
	private TextField nombreDeUsuarioField;

	@FXML
	private TextField imagenField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField nombreField;

	@FXML
	private TextField apellidoField;

	@FXML
	void handleAgreagarButton() {

		if(isInputValid()) {
			
		}
	}

	@FXML
	void handleVolverButton() {

		principalPane.showMenuPane();
	}

	@FXML
	void initialize() {
		assert nombreDeUsuarioField != null : "fx:id=\"nombreDeUsuarioField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert imagenField != null : "fx:id=\"imagenField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert nombreField != null : "fx:id=\"nombreField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert apellidoField != null : "fx:id=\"apellidoField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";

	}

	public boolean isInputValid() {

		boolean centinela = false;
		String errorMessage = "";

		if (nombreDeUsuarioField.getText().length() == 0 || nombreDeUsuarioField.getText() == null) {
			errorMessage += "Debe ingresar el nombre de usuario.\n";
		}
		if (imagenField.getText().length() == 0 || imagenField.getText() == null) {
			errorMessage += "Debe ingresar la ruta de la imagen.\n";
		}
		if (emailField.getText().length() == 0 || emailField.getText() == null) {
			errorMessage += "Debe ingresar el correo electronico.\n";
		}
		if (nombreField.getText().length() == 0 || nombreField.getText() == null) {
			errorMessage += "Debe ingresar el nombre.\n";
		}
		if (apellidoField.getText().length() == 0 || apellidoField.getText() == null) {
			errorMessage += "Debe ingresar el apellido.\n";
		}

		if (errorMessage.length() == 0) {
			centinela = true;
		} else {
			principalPane.showAlert(errorMessage, "", "ADVERTENCIA", AlertType.WARNING);
		}

		return centinela;

	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}

}
