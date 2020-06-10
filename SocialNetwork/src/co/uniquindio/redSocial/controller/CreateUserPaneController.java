package co.uniquindio.redSocial.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class CreateUserPaneController {

	private PrincipalPaneController principalPane;

	@FXML
	private TextField nombreDeUsuarioField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField nombreField;

	@FXML
	private TextField apellidoField;

	@FXML
	void handleAgreagarButton() {

		if (isInputValid()) {

			try {
				principalPane.getMain().addUser(nombreField.getText(), apellidoField.getText(),
						nombreDeUsuarioField.getText(), emailField.getText());

				principalPane.showAlert("Usuario: " + nombreDeUsuarioField.getText() + " agregado.", "", "INFORMACION",
						AlertType.INFORMATION);

				nombreDeUsuarioField.setText("");
				nombreField.setText("");
				apellidoField.setText("");
				emailField.setText("");

			} catch (NodeRepeatException e) {

				principalPane.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			}
		}
	}

	@FXML
	void handleVolverButton() {

		principalPane.showMenuPane();
	}

	@FXML
	void initialize() {
		assert nombreDeUsuarioField != null : "fx:id=\"nombreDeUsuarioField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
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

		if (emailField.getText().length() == 0 || emailField.getText() == null) {
			errorMessage += "Debe ingresar el correo electronico.\n";
		}
		else
		{
			Pattern pattern = Pattern
	                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
	        String email = emailField.getText();
	        Matcher mather = pattern.matcher(email);

	        if (!mather.find())
	        	errorMessage += "El correo: "+email+" no es vFalido.\n";
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
