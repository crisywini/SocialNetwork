package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;

public class MenuPaneController {

	private PrincipalPaneController principalPane;

	@FXML
	void handleCrearNuevoUsuarioButton() {

		principalPane.showCreateUserPane();
	}

	@FXML
	void handleIngresarACriluliButton() {
		principalPane.showSocialNetworkPane();
	}

	@FXML
	void initialize() {

	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}

}
