package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class FriendsPaneController {
	private PrincipalPaneController principalPane;
	private User user;
	private BorderPane pane;
	@FXML
	private Label nickNameUserLabel;

	@FXML
	private TableView<User> friendsTableView;

	@FXML
	private TableColumn<User, String> nickNameFriendsTableColumn;

	@FXML
	private TableColumn<User, String> nameFriendsTableColumn;

	@FXML
	private TableView<User> usersTableView;

	@FXML
	private TableColumn<User, String> nickNameUsersTableColumn;

	@FXML
	private TableColumn<User, String> nameUsersTableColumn;

	@FXML
	void handleBlockFriendButton() {
		if (isSelectedFriend()) {

		} else
			principalPane.showAlert("Debes seleccionar a alg�n amigo", "", "ADVERTENCIA", AlertType.WARNING);

	}

	@FXML
	void handleRemoveFriendButton() {
		if (isSelectedFriend()) {

		} else
			principalPane.showAlert("Debes sleccionar a a alg�n amigo", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleSendRequestButton() {
		if (isSelectedUser()) {

		} else
			principalPane.showAlert("Debes seleccionar a alg�n Usuario", "", "ADVERTENCIA", AlertType.WARNING);
	}

	@FXML
	void handleBackButton() {
		principalPane.showUserPane(user, pane);
	}

	@FXML
	void initialize() {
		assert nickNameUserLabel != null : "fx:id=\"nickNameUserLabel\" was not injected: check your FXML file 'FriendsPane.fxml'.";
		assert friendsTableView != null : "fx:id=\"friendsTableView\" was not injected: check your FXML file 'FriendsPane.fxml'.";
		assert nickNameFriendsTableColumn != null : "fx:id=\"nickNameFriendsTableColumn\" was not injected: check your FXML file 'FriendsPane.fxml'.";
		assert nameFriendsTableColumn != null : "fx:id=\"nameFriendsTableColumn\" was not injected: check your FXML file 'FriendsPane.fxml'.";
		assert usersTableView != null : "fx:id=\"usersTableView\" was not injected: check your FXML file 'FriendsPane.fxml'.";
		assert nickNameUsersTableColumn != null : "fx:id=\"nickNameUsersTableColumn\" was not injected: check your FXML file 'FriendsPane.fxml'.";
		assert nameUsersTableColumn != null : "fx:id=\"nameUsersTableColumn\" was not injected: check your FXML file 'FriendsPane.fxml'.";
	}

	public void fillFriendsTableView() {
		nickNameFriendsTableColumn.setCellValueFactory(cellData -> cellData.getValue().nick_NameProperty());
		nameFriendsTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		Main.friendsData.clear();
		Main.friendsData.setAll(user.friendsInArrayList());
		friendsTableView.setItems(Main.friendsData);
	}

	public void fillUsersTableView() {
		nickNameUsersTableColumn.setCellValueFactory(cellData -> cellData.getValue().nick_NameProperty());
		nameUsersTableColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		Main.usersData.clear();
		Main.usersData.setAll(principalPane.getMain().getMySocialNetwork().getUsersInArrayList());
	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
		fillUsersTableView();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		fillFriendsTableView();
	}

	public BorderPane getPane() {
		return pane;
	}

	public void setPane(BorderPane pane) {
		this.pane = pane;
	}

	public boolean isSelectedFriend() {
		int pos = friendsTableView.getSelectionModel().getSelectedIndex();
		return pos != -1;
	}

	public boolean isSelectedUser() {
		int pos = usersTableView.getSelectionModel().getSelectedIndex();
		return pos != -1;
	}

}
