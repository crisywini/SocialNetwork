package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class PrincipalPaneController {

	private Main main;

	@FXML
	private BorderPane principalPane;

	AnchorPane createUserPane;
	AnchorPane menuPane;
	AnchorPane userPane;
	BorderPane socialNetworkPane;
	CreateUserPaneController createUserPaneController;
	MenuPaneController menuPaneController;
	UserPaneController userPaneController;
	SocialNetworkPaneController socialNetworkPaneController;

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

	public void showUserPane(User user, BorderPane pane) {
		if (userPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/UserPane.fxml"));
				userPane = (AnchorPane) loader.load();
				UserPaneController controller = loader.getController();
				controller.setPrincipalPane(this);
				controller.setUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		pane.setCenter(userPane);
	}

	public void showSocialNetworkPane() {
		if (socialNetworkPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/SocialNetworkPane.fxml"));
				socialNetworkPane = (BorderPane) loader.load();
				socialNetworkPaneController = loader.getController();
				socialNetworkPaneController.setPrincipalPane(this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		socialNetworkPaneController.fillMenuFriendsHBox();
		principalPane.setCenter(socialNetworkPane);
	}

	public void showAlert(String message, String header, String title, AlertType alertType) {

		Alert alert = new Alert(alertType);

		alert.setContentText(message);
		alert.setHeaderText(header);
		alert.setTitle(title);

		alert.showAndWait();
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

}
