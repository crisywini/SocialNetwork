package co.uniquindio.redSocial.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrincipalPaneController {

	private Main principal;
	AnchorPane loginPane;
	AnchorPane createUserPane;
	LoginPaneController loginPaneController;
	CreateUserPaneController createUserPaneController;
	@FXML
	private BorderPane principalPane;

	@FXML
	void initialize() {
		showLoginPane();
	}

	public void showLoginPane() {
		if (loginPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/LoginPane.fxml"));
				loginPane = (AnchorPane) loader.load();
				loginPaneController = loader.getController();
				loginPaneController.setPrincipalPane(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		principalPane.setCenter(loginPane);
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

	public Main getPrincipal() {
		return principal;
	}

	public void setPrincipal(Main principal) {
		this.principal = principal;
	}
}
