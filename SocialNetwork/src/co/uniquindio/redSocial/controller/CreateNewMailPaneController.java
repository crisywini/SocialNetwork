package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.BlockedFriendException;
import co.uniquindio.redSocial.exceptions.NotFriendsException;
import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.util.LinkedList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class CreateNewMailPaneController {
	private User user;
	private BorderPane pane;
	private PrincipalPaneController principalPane;
	private LinkedList<User> users = new LinkedList<User>();
	private String usersArea = "";

	@FXML
	private Label nickNameLabel;

	@FXML
	private TextArea usersTextArea;

	@FXML
	private TextArea messageTextArea;

	@FXML
	private TableView<User> friendsTableView;

	@FXML
	private TableColumn<User, String> friendsNickTableColumn;

	@FXML
	private TableColumn<User, String> friendsNameTableColumn;

	@FXML
	void handleBackButton() {
		usersTextArea.setText("");
		messageTextArea.setText("");
		principalPane.showMessagesPane(user, pane);
	}

	@FXML
	void handleSelectFriendButton() {
		if (isSelectedFriend()) {
			User friendSelected = friendsTableView.getSelectionModel().getSelectedItem();
			if (!friendSelected.getNick_name().equals(user.getNick_name())) {
				try {
					if (!users.isOnList(friendSelected)) {
						setUsersArea(friendSelected.getNick_name() + " ");
						usersTextArea.setText(usersArea);
						users.addLast(friendSelected);
					}
				} catch (BigIndexException e) {
					e.printStackTrace();
				}
			} else
				principalPane.showAlert("Debes seleccionar a alguien distinto a ti", "", "ADVERTENCIA",
						AlertType.WARNING);
		} else
			principalPane.showAlert("Debes seleccionar a alguien", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleSendButton() {
		if (usersTextArea.getText().length() != 0) {
			if (messageTextArea.getText().length() != 0) {
				try {
					user.sendMessage(users, messageTextArea.getText());
					messageTextArea.setText("");
					usersTextArea.setText("");
					usersArea = "";
					users.clear();
					principalPane.showAlert("Mensaje enviado", "", "Informacion", AlertType.INFORMATION);
				} catch (NotFriendsException e) {
					e.printStackTrace();
				} catch (BigIndexException e) {
					e.printStackTrace();
				} catch (BlockedFriendException e) {
					principalPane.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
				}
			}
		} else
			principalPane.showAlert("Debes seleccionar a quien vas a enviar el mensaje", "", "ADVERTENCIA",
					AlertType.WARNING);
	}

	@FXML
	void initialize() {
		assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'CreateNewMailPane.fxml'.";
		assert usersTextArea != null : "fx:id=\"usersTextArea\" was not injected: check your FXML file 'CreateNewMailPane.fxml'.";
		assert messageTextArea != null : "fx:id=\"messageTextArea\" was not injected: check your FXML file 'CreateNewMailPane.fxml'.";
		assert friendsTableView != null : "fx:id=\"friendsTableView\" was not injected: check your FXML file 'CreateNewMailPane.fxml'.";
		assert friendsNickTableColumn != null : "fx:id=\"friendsNickTableColumn\" was not injected: check your FXML file 'CreateNewMailPane.fxml'.";
		assert friendsNameTableColumn != null : "fx:id=\"friendsNameTableColumn\" was not injected: check your FXML file 'CreateNewMailPane.fxml'.";
	}

	public void fillFriendsTV() {
		friendsNickTableColumn.setCellValueFactory(cellData -> cellData.getValue().nick_NameProperty());
		friendsNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		Main.friendsData.clear();
		Main.friendsData.setAll(user.friendsInArrayList());
		friendsTableView.setItems(Main.friendsData);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		nickNameLabel.setText(user.getNick_name());
		fillFriendsTV();
	}

	public BorderPane getPane() {
		return pane;
	}

	public void setPane(BorderPane pane) {
		this.pane = pane;
	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}

	public boolean isSelectedFriend() {
		int pos = friendsTableView.getSelectionModel().getSelectedIndex();
		return pos != -1;
	}

	public void setUsersArea(String usersArea) {
		this.usersArea += usersArea;
	}
}
