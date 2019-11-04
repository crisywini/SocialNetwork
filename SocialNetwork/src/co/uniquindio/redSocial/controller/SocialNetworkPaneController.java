package co.uniquindio.redSocial.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import co.uniquindio.redSocial.model.SocialNetwork;
import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.util.Graph;
import co.uniquindio.redSocial.util.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class SocialNetworkPaneController {

	private PrincipalPaneController principalPane;
	private ArrayList<Button> friendsButton = new ArrayList<Button>();
	private EventHandler<ActionEvent> handlerFriendsButton = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button auxiliar = (Button) event.getSource();
			SocialNetwork network = principalPane.getMain().getMySocialNetwork();
			Graph<User> users = network.getUsers();
			User user;
			for (int i = 0; i < friendsButton.size(); i++) {
				if (friendsButton.get(i).getId().equals(auxiliar.getId())) {
					user = users.getGraph().get(auxiliar.getId()).getValue();
					principalPane.showUserPane(user, menuPane);
				}
			}
		}
	};

	@FXML
	private BorderPane menuPane;

	@FXML
	private HBox menuFriendsHBox;

	@FXML
	void initialize() {
		assert menuPane != null : "fx:id=\"menuPane\" was not injected: check your FXML file 'SocialNetworkPane.fxml'.";
		assert menuFriendsHBox != null : "fx:id=\"menuFriendsHBox\" was not injected: check your FXML file 'SocialNetworkPane.fxml'.";
	}

	@FXML
	void handleVolverButton() {
		principalPane.showMenuPane();
	}

	public void fillMenuFriendsHBox() {
		Main main = principalPane.getMain();
		SocialNetwork network = main.getMySocialNetwork();
		Label information = new Label("No hay usuarios!");
		friendsButton.clear();
		if (network.getUsers().getSize() == 0) {
			menuFriendsHBox.getChildren().add(information);
		} else {
			menuFriendsHBox.getChildren().clear();
			Graph<User> users = network.getUsers();
			User auxiliar;
			HashMap<String, Node<User>> hashMapUser = users.getGraph();
			Iterator<String> iterator = hashMapUser.keySet().iterator();
			while (iterator.hasNext()) {
				auxiliar = hashMapUser.get(iterator.next()).getValue();
				Button newFriendButton = new Button(auxiliar.getNick_name());
				newFriendButton.setId(auxiliar.getNick_name());
				newFriendButton.setStyle("-fx-background-radius: 20px;");
				newFriendButton.setOnAction(handlerFriendsButton);
				friendsButton.add(newFriendButton);
			}
		}
		ObservableList<javafx.scene.Node> buttons = FXCollections.observableArrayList(friendsButton);
		menuFriendsHBox.getChildren().addAll(buttons);
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}
}
