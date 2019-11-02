package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;

public class SocialNetworkPaneController {

	private PrincipalPaneController principalPane;

    @FXML
    private BorderPane menuPane;

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
