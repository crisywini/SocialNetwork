package co.uniquindio.redSocial.controller;

import java.util.ArrayList;


import java.util.HashMap;
import java.util.Iterator;

import co.uniquindio.redSocial.model.SocialNetwork;
import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.util.Graph;
import co.uniquindio.redSocial.util.Node;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class SocialNetworkPaneController {

	private Menu usersMenu;
	private PrincipalPaneController principalPane;
	private ArrayList<MenuItem> friendsMenuItem = new ArrayList<MenuItem>();
	private EventHandler<ActionEvent> handlerFriendsMenuItem = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			MenuItem auxiliar = (MenuItem) event.getSource();
			SocialNetwork network = principalPane.getMain().getMySocialNetwork();
			Graph<User> users = network.getUsers();
			User user;
			for (int i = 0; i < friendsMenuItem.size(); i++) {
				if (friendsMenuItem.get(i).getId().equals(auxiliar.getId())) {
					user = users.getGraph().get(auxiliar.getId()).getValue();
					principalPane.showUserPane(user, menuPane);
				}
			}
		}
	};

	@FXML
	private BorderPane menuPane;

	@FXML
	void initialize() {
		assert menuPane != null : "fx:id=\"menuPane\" was not injected: check your FXML file 'SocialNetworkPane.fxml'.";
	}

	@FXML
	void handleVolverButton() {
		principalPane.showMenuPane();
	}

	public void fillMenuFriendsHBox() {
		Main main = principalPane.getMain();
		SocialNetwork network = main.getMySocialNetwork();
		Label information = new Label("No hay usuarios!");
		information.setFont(new Font("Lucida Sans Unicode", 24));
		friendsMenuItem.clear();
		if (network.getUsers().getSize() == 0) {
			menuPane.setCenter(information);
		} else {
			usersMenu.getItems().clear();
			Graph<User> users = network.getUsers();
			User auxiliar;
			HashMap<String, Node<User>> hashMapUser = users.getGraph();
			Iterator<String> iterator = hashMapUser.keySet().iterator();
			while (iterator.hasNext()) {
				auxiliar = hashMapUser.get(iterator.next()).getValue();
				MenuItem newFriendItem = new MenuItem(auxiliar.getNick_name());
				newFriendItem.setId(newFriendItem.getText());
				newFriendItem.setOnAction(handlerFriendsMenuItem);
				friendsMenuItem.add(newFriendItem);
			}
		}
		usersMenu.getItems().clear();
		usersMenu.getItems().addAll(friendsMenuItem);
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}

	public Menu getUsersMenu() {
		return usersMenu;
	}

	public void setUsersMenu(Menu usersMenu) {
		this.usersMenu = usersMenu;
	}
}
