package co.uniquindio.redSocial.controller;

import java.io.File;

import java.io.IOException;

import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeGraphWithLinksException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.model.Mail;
import co.uniquindio.redSocial.model.SocialNetwork;
import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.persistence.Archivo;
import co.uniquindio.redSocial.persistence.Persistencia;
import co.uniquindio.redSocial.util.Graph;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application implements ISocialNetworkControl {

	public static SocialNetwork mySocialNetwork;
	public static ObservableList<Mail> mailsData = FXCollections.observableArrayList();
	public static ObservableList<User> usersData = FXCollections.observableArrayList();
	public static ObservableList<User> friendsData = FXCollections.observableArrayList();
	public static ObservableList<User> requestedFriendsData = FXCollections.observableArrayList();
	public static ObservableList<User> blockedFriendsData = FXCollections.observableArrayList();
	static EventHandler<WindowEvent> closer = new EventHandler<WindowEvent>() {

		@Override
		public void handle(WindowEvent event) {
			if (PrincipalPaneController.elegir("¿Desea guardar los datos?")) {
				try {
					serializeSocialNetwork();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	};

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
			primaryStage.setOnCloseRequest(closer);
			primaryStage.getIcons().add(new Image("file:///C:/Users/Crisi/Desktop/Proyectos_Java/Proyectos_Estructuras/RedSocial/respositorio/SocialNetwork/src/co/uniquindio/redSocial/images/icono.png"));
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
		Main.mySocialNetwork = mySocialNetwork;
	}

	public static ObservableList<Mail> getMailsData() {
		return mailsData;
	}

	public static void setMailsData(ObservableList<Mail> mailsData) {
		Main.mailsData = mailsData;
	}

	public static ObservableList<User> getUsersData() {
		return usersData;
	}

	public static void setUsersData(ObservableList<User> usersData) {
		Main.usersData = usersData;
	}

	public static ObservableList<User> getFriendsData() {
		return friendsData;
	}

	public static void setFriendsData(ObservableList<User> friendsData) {
		Main.friendsData = friendsData;
	}

	public static ObservableList<User> getRequestedFriendsData() {
		return requestedFriendsData;
	}

	public static void setRequestedFriendsData(ObservableList<User> requestedFriendsData) {
		Main.requestedFriendsData = requestedFriendsData;
	}

	public static ObservableList<User> getBlockedFriendsData() {
		return blockedFriendsData;
	}

	public static void setBlockedFriendsData(ObservableList<User> blockedFriendsData) {
		Main.blockedFriendsData = blockedFriendsData;
	}

	// -----------------------------Persistence-------------------------
	public static void serializeSocialNetwork() throws IOException {
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
	public void addUser(String name, String surname, String nick_name, String email) throws NodeRepeatException {
		getMySocialNetwork().addUser(name, surname, nick_name, email);
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
