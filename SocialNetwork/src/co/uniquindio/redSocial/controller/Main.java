package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeGraphWithLinksException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.model.SocialNetwork;
import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.util.Graph;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements ISocialNetworkControl {

	private SocialNetwork mySocialNetwork;

	@Override
	public void start(Stage primaryStage) {
		mySocialNetwork = new SocialNetwork("Criluli");
		primaryStage.setTitle(mySocialNetwork.getName());
		showPrincipalStage(primaryStage);
	}

	public void showPrincipalStage(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/PrincipalPane.fxml"));
			BorderPane principalPane = (BorderPane) loader.load();
			Scene scene = new Scene(principalPane);
			PrincipalPaneController controller = loader.getController();
			controller.setPrincipal(this);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	// ---------------------------Getters and setters-----------------------

	public SocialNetwork getMySocialNetwork() {
		return mySocialNetwork;
	}

	public void setMySocialNetwork(SocialNetwork mySocialNetwork) {
		this.mySocialNetwork = mySocialNetwork;
	}
	// ----------------------------Services----------------------------

	@Override
	public void addUser(String id, String name, String surname, String nick_name, String email, String password,
			String image) throws NodeRepeatException {
		getMySocialNetwork().addUser(id, name, surname, nick_name, email, password, image);
	}

	@Override
	public User removeUser(String nick_name) throws NodeGraphWithLinksException, NodeGraphNullException {
		return getMySocialNetwork().removeUser(nick_name);
	}

	@Override
	public Graph<User> getUsers() {
		return getMySocialNetwork().getUsers();
	}

	@Override
	public void setUsers(Graph<User> users) {
		getMySocialNetwork().setUsers(users);
	}

	@Override
	public String getName() {
		return getMySocialNetwork().getName();
	}

	@Override
	public void setName(String name) {
		getMySocialNetwork().setName(name);
	}
}
