package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.exceptions.UnblockedFriendException;
import co.uniquindio.redSocial.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class BlockedFriendsPaneController {
	private User user;
	private BorderPane pane;
	private PrincipalPaneController principalPane;
	@FXML
	private Label nickNameLabel;

	@FXML
	private TableView<User> blockedFriendsTableView;

	@FXML
	private TableColumn<User, String> blockedFriendsNickTableColumn;

	@FXML
	private TableColumn<User, String> blockedFriendsNameTableColumn;

	@FXML
	void handleBackButton() {
		principalPane.showUserPane(user, pane);
	}

	@FXML
	void handleUnblockedButton() {
		if (isSelectedUser()) {
			User selectedUser = blockedFriendsTableView.getSelectionModel().getSelectedItem();
			try {
				user.unblockFriend(selectedUser);
				principalPane.showAlert("Has desbloqueado al usuario: " + selectedUser.getNick_name(), "",
						"Informacion", AlertType.INFORMATION);
				blockedFriendsTableView.getItems().remove(selectedUser);
			} catch (BigIndexException e) {
				e.printStackTrace();
			} catch (NodeRepeatException e) {
				e.printStackTrace();
			} catch (UnblockedFriendException e) {
				principalPane.showAlert(e.getMessage(), "", "ERROR", AlertType.ERROR);
			}
		} else
			principalPane.showAlert("Debes seleccionar algún usuario!", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void initialize() {
		assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'BlockedFriendsPane.fxml'.";
		assert blockedFriendsTableView != null : "fx:id=\"blockedFriendsTableView\" was not injected: check your FXML file 'BlockedFriendsPane.fxml'.";
		assert blockedFriendsNickTableColumn != null : "fx:id=\"blockedFriendsNickTableColumn\" was not injected: check your FXML file 'BlockedFriendsPane.fxml'.";
		assert blockedFriendsNameTableColumn != null : "fx:id=\"blockedFriendsNameTableColumn\" was not injected: check your FXML file 'BlockedFriendsPane.fxml'.";
	}

	public void fillBlockedFriendsTV() {
		blockedFriendsNickTableColumn.setCellValueFactory(cellData -> cellData.getValue().nick_NameProperty());
		blockedFriendsNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		Main.blockedFriendsData.clear();
		try {
			if (!user.getBlockedFriends().isEmpty()) {
				Main.blockedFriendsData.setAll(user.getBlockedFriendsInList());
				blockedFriendsTableView.setItems(Main.blockedFriendsData);
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
		nickNameLabel.setText(user.getNick_name());
		fillBlockedFriendsTV();
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

	public boolean isSelectedUser() {
		int pos = blockedFriendsTableView.getSelectionModel().getSelectedIndex();
		return pos != -1;
	}
}
