package co.uniquindio.redSocial.controller;

import java.util.Optional;

import co.uniquindio.redSocial.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalPaneController {

	private Main main;

	@FXML
	private BorderPane principalPane;
	@FXML
	private Menu usersMenu;
	private Stage primaryStage;

	AnchorPane createUserPane;
	AnchorPane menuPane;
	AnchorPane userPane;
	BorderPane socialNetworkPane;
	AnchorPane messagesPane;
	AnchorPane friendsPane;
	AnchorPane requestedPane;
	AnchorPane blockedFriendsPane;
	AnchorPane createNewMailPane;
	CreateUserPaneController createUserPaneController;
	MenuPaneController menuPaneController;
	UserPaneController userPaneController;
	SocialNetworkPaneController socialNetworkPaneController;
	MessagesPaneController messagesController;
	FriendsPaneController friendsController;
	RequestedFriendsPaneController requestedFriendsController;
	BlockedFriendsPaneController blockedController;
	CreateNewMailPaneController createNewMailPaneController;

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
				userPaneController = loader.getController();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		userPaneController.setPrincipalPane(this);
		userPaneController.setPane(pane);
		userPaneController.setUser(user);
		pane.setCenter(userPane);
	}

	public void showSocialNetworkPane() {
		if (socialNetworkPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/SocialNetworkPane.fxml"));
				socialNetworkPane = (BorderPane) loader.load();
				socialNetworkPaneController = loader.getController();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		socialNetworkPaneController.setPrincipalPane(this);
		socialNetworkPaneController.setUsersMenu(usersMenu);
		socialNetworkPaneController.fillMenuFriendsHBox();
		principalPane.setCenter(socialNetworkPane);
	}

	public void showMessagesPane(User user, BorderPane pane) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/MessagesPane.fxml"));
			messagesPane = (AnchorPane) loader.load();
			messagesController = loader.getController();

		} catch (Exception e) {
			e.printStackTrace();
		}

		messagesController.setPane(pane);
		messagesController.setUser(user);
		messagesController.setPrincipalPane(this);
		pane.setCenter(messagesPane);
	}

	public void showFriendsPane(User user, BorderPane pane) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/FriendsPane.fxml"));
			friendsPane = (AnchorPane) loader.load();
			friendsController = loader.getController();
		} catch (Exception e) {
			e.printStackTrace();
		}
		friendsController.setPrincipalPane(this);
		friendsController.setUser(user);
		friendsController.setPane(pane);
		pane.setCenter(friendsPane);
	}

	public void showRequestedFriendsPane(User user, BorderPane pane) {
		if (requestedPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/RequestedFriendsPane.fxml"));
				requestedPane = (AnchorPane) loader.load();
				requestedFriendsController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		requestedFriendsController.setPane(pane);
		requestedFriendsController.setUser(user);
		requestedFriendsController.setPrincipalPane(this);
		pane.setCenter(requestedPane);
	}

	public void showBlockedFriendsPane(User user, BorderPane pane) {
		if (blockedFriendsPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/BlockedFriendsPane.fxml"));
				blockedFriendsPane = (AnchorPane) loader.load();
				blockedController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		blockedController.setPane(pane);
		blockedController.setUser(user);
		blockedController.setPrincipalPane(this);
		pane.setCenter(blockedFriendsPane);
	}

	public void showCreateNewMailPane(User user, BorderPane pane) {
		if (createNewMailPane == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("../view/CreateNewMailPane.fxml"));
				createNewMailPane = (AnchorPane) loader.load();
				createNewMailPaneController = loader.getController();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		createNewMailPaneController.setPane(pane);
		createNewMailPaneController.setUser(user);
		createNewMailPaneController.setPrincipalPane(this);
		pane.setCenter(createNewMailPane);
	}

	public void showAlert(String message, String header, String title, AlertType alertType) {
		Alert alert = new Alert(alertType);
		alert.initOwner(getPrimaryStage());
		alert.setContentText(message);
		alert.setHeaderText(header);
		alert.setTitle(title);
		alert.showAndWait();
	}

	public static boolean elegir(String mensaje) {
		boolean centinela;
		Alert miAlert = new Alert(AlertType.CONFIRMATION);
		miAlert.setContentText(mensaje);
		miAlert.setHeaderText("");
		ButtonType buttonTypeOne = new ButtonType("Si");
		ButtonType buttonTypeTwo = new ButtonType("No");
		miAlert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> resultado = miAlert.showAndWait();
		centinela = resultado.get() == buttonTypeOne;
		return centinela;
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

}
