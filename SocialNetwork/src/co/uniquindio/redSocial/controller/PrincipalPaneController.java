package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrincipalPaneController {

	@FXML
	private BorderPane principalPane;

	AnchorPane createUserPane;
	AnchorPane menuPane;
	CreateUserPaneController createUserPaneController;
	MenuPaneController menuPaneController;

	@FXML
	void initialize() {

		showMenuPane();
	}

	public void showMenuPane() {

		if (menuPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/MenuPane.fxml"));
				menuPane = (AnchorPane) loader.load();
				menuPaneController = loader.getController();
				menuPaneController.setPrincipalPane(this);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(menuPane);
	}

	public void showCreateUserPane() {

		if (createUserPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/CreateUserPane.fxml"));
				createUserPane = (AnchorPane) loader.load();
				createUserPaneController = loader.getController();
				createUserPaneController.setPrincipalPane(this);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(createUserPane);
	}

	public void showAlert(String message, String header, String title, AlertType alertType) {

		Alert alert = new Alert(alertType);

		alert.setContentText(message);
		alert.setHeaderText(header);
		alert.setTitle(title);
		
		alert.showAndWait();
	}
}
