package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;

public class UserPaneController {

	private PrincipalPaneController principalPane;

	@FXML
	private Label nickNameLabel;

	@FXML
	private TextArea postTextArea;

	@FXML
	private ScrollPane scrollPane;

	@FXML
	void handleAmigosButton() {

	}

	@FXML
	void handleBloqueadosButton() {

	}

	@FXML
	void handleMensajesButton() {

	}

	@FXML
	void handlePublicarButton() {

	}

	@FXML
	void handleSolicitudesButton() {

	}

	@FXML
	void initialize() {
		assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert postTextArea != null : "fx:id=\"postTextArea\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert scrollPane != null : "fx:id=\"scrollPane\" was not injected: check your FXML file 'UserPane.fxml'.";
	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}
//	public VBox getPost()
//	{
//		VBox pane = new VBox();
//		pane.setAlignment(Pos.CENTER);
//		
//		
//	}
}