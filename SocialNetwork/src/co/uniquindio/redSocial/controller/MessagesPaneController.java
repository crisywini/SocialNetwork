package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.EmptyLinkedListException;
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
		principalPane.showCreateNewMailPane(user, pane);
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
		try {
			if (!user.getMails().isEmpty()) {
				Main.mailsData.clear();
				Main.mailsData.setAll(user.getMailsArrayList());
				messagesTableView.setItems(Main.mailsData);
			}
			else
				messagesTableView.setId(null);
		} catch (EmptyLinkedListException | BigIndexException e) {
			e.printStackTrace();
		}
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
		initMessageTV();
		nickNameLabel.setText(user.getNick_name());
	}

	public BorderPane getPane() {
		return pane;
	}

	public void setPane(BorderPane pane) {
		this.pane = pane;
	}

}
