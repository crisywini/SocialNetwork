package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.model.Mail;
import co.uniquindio.redSocial.model.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

public class MessagesPaneController {

	private PrincipalPaneController principalPane;
	private User user;
	private BorderPane pane;
	@FXML
	private Label nickNameLabel;

	@FXML
	private TableView<Mail> messagesTableView;

	@FXML
	private TableColumn<Mail, String> usersTableColumn;

	@FXML
	private TableColumn<Mail, String> messagesTableColumn;

	@FXML
	void handleBackButton() {
		principalPane.showUserPane(user, pane);
	}

	@FXML
	void handleSendNewMessageButton() {

	}

	@FXML
	void initialize() {
		assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'SendMessageView.fxml'.";
		assert messagesTableView != null : "fx:id=\"messagesTableView\" was not injected: check your FXML file 'SendMessageView.fxml'.";
		assert usersTableColumn != null : "fx:id=\"usersTableColumn\" was not injected: check your FXML file 'SendMessageView.fxml'.";
		assert messagesTableColumn != null : "fx:id=\"messagesTableColumn\" was not injected: check your FXML file 'SendMessageView.fxml'.";
	}

	public void initMessageTV() {
		usersTableColumn.setCellValueFactory(cellData -> cellData.getValue().transmitterNickNameProperty());
		messagesTableColumn.setCellValueFactory(cellData -> cellData.getValue().messageProperty());
	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		nickNameLabel.setText(user.getNick_name());
	}

	public BorderPane getPane() {
		return pane;
	}

	public void setPane(BorderPane pane) {
		this.pane = pane;
	}

}
