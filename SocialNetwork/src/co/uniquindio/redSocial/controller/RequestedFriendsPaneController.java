package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.exceptions.NotFriendsException;
import co.uniquindio.redSocial.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class RequestedFriendsPaneController {
	private User user;
	private BorderPane pane;
	private PrincipalPaneController principalPane;

	@FXML
	private Label userNickNameLabel;

	@FXML
	private TableView<User> requestedFriendsTableView;

	@FXML
	private TableColumn<User, String> requestedFriendsNickTableColumn;

	@FXML
	private TableColumn<User, String> requestedFriendsNameTableColumn;

	@FXML
	void handleAcceptRequestButton() {
		if (isUserSelected()) {
			User userSelected = requestedFriendsTableView.getSelectionModel().getSelectedItem();
			try {
				user.acceptRequest(userSelected);
				principalPane.showAlert("El usuario: " + userSelected.getNick_name() + " ha sido agregado a tus amigos",
						"", "Informacion", AlertType.INFORMATION);
				requestedFriendsTableView.getItems().remove(userSelected);
			} catch (BigIndexException e) {
				e.printStackTrace();
			} catch (NodeRepeatException e) {
				principalPane.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			} catch (NotFriendsException e) {
				e.printStackTrace();
			} catch (NodeGraphNullException e) {
				e.printStackTrace();
			}
		} else
			principalPane.showAlert("Debes seleccionar un usuario", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleBackButton() {
		principalPane.showUserPane(user, pane);
	}

	@FXML
	void handleRemoveRequestButton() {
		if (isUserSelected()) {
			User userSelected = requestedFriendsTableView.getSelectionModel().getSelectedItem();
			try {
				user.removeRequest(userSelected);
				principalPane.showAlert(
						"Has eliminado el usuario: " + userSelected.getNick_name() + " de las solicitudes de amistad",
						"", "Informacion", AlertType.INFORMATION);
				requestedFriendsTableView.getItems().remove(userSelected);

			} catch (BigIndexException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void initialize() {
		assert userNickNameLabel != null : "fx:id=\"userNickNameLabel\" was not injected: check your FXML file 'RequestedFriendsPane.fxml'.";
		assert requestedFriendsTableView != null : "fx:id=\"requestedFriendsTableView\" was not injected: check your FXML file 'RequestedFriendsPane.fxml'.";
		assert requestedFriendsNickTableColumn != null : "fx:id=\"requestedFriendsNickTableColumn\" was not injected: check your FXML file 'RequestedFriendsPane.fxml'.";
		assert requestedFriendsNameTableColumn != null : "fx:id=\"requestedFriendsNameTableColumn\" was not injected: check your FXML file 'RequestedFriendsPane.fxml'.";
	}

	public void fillRequestedFriendsTV() {
		requestedFriendsNickTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		requestedFriendsNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nick_NameProperty());
		Main.requestedFriendsData.clear();
		try {
			if (!user.getRequestedFriendsInList().isEmpty()) {
				Main.requestedFriendsData.setAll(user.getRequestedFriendsInList());
				requestedFriendsTableView.setItems(Main.requestedFriendsData);
			}
		} catch (BigIndexException e) {
			e.printStackTrace();
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		userNickNameLabel.setText(user.getNick_name());
		fillRequestedFriendsTV();
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

	public boolean isUserSelected() {
		int pos = requestedFriendsTableView.getSelectionModel().getSelectedIndex();
		return pos != -1;
	}
}
