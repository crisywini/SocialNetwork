package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPaneController {
	private PrincipalPaneController principalPane;

	@FXML
	private TextField nickNameField;

	@FXML
	private PasswordField passwordField;

	@FXML
	void handleCrearUnaCuentaButton() {
		getPrincipalPane().showCreateUserPane();
	}

	@FXML
	void handleIngresaButton() {
	}

	@FXML
	void initialize() {
		assert nickNameField != null : "fx:id=\"nickNameField\" was not injected: check your FXML file 'LoginPane.fxml'.";
		assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginPane.fxml'.";

	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}
}
