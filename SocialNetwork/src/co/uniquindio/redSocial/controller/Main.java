package co.uniquindio.redSocial.controller;

import java.io.File;
import java.io.IOException;

import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeGraphWithLinksException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.model.SocialNetwork;
import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.persistence.Archivo;
import co.uniquindio.redSocial.persistence.Persistencia;
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
		loadData();
		primaryStage.setTitle(mySocialNetwork.getName());
		showPrincipalStage(primaryStage);
	}

	public void showPrincipalStage(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("../view/PrincipalPane.fxml"));
			BorderPane principalPane = (BorderPane) loader.load();
			Scene scene = new Scene(principalPane);
			PrincipalPaneController principalController = loader.getController();
			principalController.setMain(this);
			principalController.setPrimaryStage(primaryStage);
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

	// -----------------------------Persistence-------------------------
	public void serializeSocialNetwork() throws IOException {
		Persistencia.serializarObjeto(Persistencia.SOCIAL_NETWORK_DAT, mySocialNetwork);
	}

	public Object deserializeObject() throws ClassNotFoundException, IOException {
		return Persistencia.deserializarObjeto(Persistencia.SOCIAL_NETWORK_DAT);
	}

	public void saveData() throws IOException {
		serializeSocialNetwork();
	}

	public void createFiles() {
		if (!Archivo.isCreatedFile(Persistencia.SOCIAL_NETWORK_DAT))
			try {
				serializeSocialNetwork();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public void loadData() {
		File socialNetowrkDat = new File(Persistencia.SOCIAL_NETWORK_DAT);
		if (socialNetowrkDat.exists()) {
			SocialNetwork socialNetworkAux;
			try {
				socialNetworkAux = (SocialNetwork) deserializeObject();
				setMySocialNetwork(socialNetworkAux);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			mySocialNetwork = new SocialNetwork("Criluli");
			createFiles();
		}
	}
	// ----------------------------Services----------------------------

	@Override
	public void addUser(String name, String surname, String nick_name, String email, String image)
			throws NodeRepeatException {
		getMySocialNetwork().addUser(name, surname, nick_name, email, image);
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
