package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CreateUserPaneController {
	private PrincipalPaneController principalPane;
	@FXML
	private TextField idField;

	@FXML
	private TextField nombreField;

	@FXML
	private TextField apellidoField;

	@FXML
	private TextField nickNameField;

	@FXML
	private TextField emailField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private PasswordField reppasswordField;

	@FXML
	private TextField imageField;

	@FXML
	void handleIngresarButton() {

	}

	@FXML
	void handleVolverButton() {
		getPrincipalPane().showLoginPane();
	}

	@FXML
	void initialize() {
		assert idField != null : "fx:id=\"idField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert nombreField != null : "fx:id=\"nombreField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert apellidoField != null : "fx:id=\"apellidoField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert nickNameField != null : "fx:id=\"nickNameField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert emailField != null : "fx:id=\"emailField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert reppasswordField != null : "fx:id=\"reppasswordField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";
		assert imageField != null : "fx:id=\"imageField\" was not injected: check your FXML file 'CreateUserPane.fxml'.";

	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}
}
