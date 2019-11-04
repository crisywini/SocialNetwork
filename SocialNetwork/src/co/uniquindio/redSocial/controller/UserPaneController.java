package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UserPaneController {

	private PrincipalPaneController principalPane;
	private User user;
	private ObservableList<Node> handlePostVBox = FXCollections.observableArrayList();
	final EventHandler<ActionEvent> handleButtons = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			Button auxiliar = (Button) event.getSource();
			if (auxiliar.getId().equals("likeBtn")) {

			}
			if (auxiliar.getId().equals("comentarBtn")) {

			}
			if (auxiliar.getId().equals("seeCommentBtn")) {

			}
		}
	};
	@FXML
	private Label nickNameLabel;

	@FXML
	private TextArea postTextArea;

	@FXML
	private VBox postVBox;

	@FXML
	void handleAmigosButton() {

	}

	@FXML
	void handleBloqueadosButton() {

	}

	@FXML
	void handleMensajesButton() {

	}

	@FXML
	void handlePublicarButton() {
		if (postTextArea.getText() == null || postTextArea.getText().length() == 0)
			principalPane.showAlert("No tiene contenido tu post!", "", "Advertencia", AlertType.WARNING);
		else {
			postVBox.getChildren().removeAll(handlePostVBox);
			VBox newCommentVBox = getComment();
			handlePostVBox.add(newCommentVBox);
			FXCollections.reverse(handlePostVBox);
			postVBox.getChildren().addAll(handlePostVBox);
			postTextArea.setText("");
		}
	}

	@FXML
	void handleSolicitudesButton() {

	}

	@FXML
	void initialize() {
		assert nickNameLabel != null : "fx:id=\"nickNameLabel\" was not injected: check your FXML file 'UserPane.fxml'.";
		assert postTextArea != null : "fx:id=\"postTextArea\" was not injected: check your FXML file 'UserPane.fxml'.";
	}

	public PrincipalPaneController getPrincipalPane() {
		return principalPane;
	}

	public void setPrincipalPane(PrincipalPaneController principalPane) {
		this.principalPane = principalPane;
	}

	public VBox getComment() {
		VBox postVBox = new VBox(70);
		postVBox.setId("vbox" + postTextArea.getText());
		postVBox.setAlignment(Pos.CENTER);
		postVBox.setMinSize(350, 150);
		postVBox.setMaxSize(350, 150);
		postVBox.setStyle("-fx-border-color: #d6d3d0;");
		Label postLabel = new Label();
		postLabel.setText(postTextArea.getText());
		postVBox.getChildren().add(postLabel);
		HBox postHBox = new HBox(10);
		postHBox.setAlignment(Pos.CENTER);
		postHBox.setStyle("-fx-border-color: #d6d3d0;");
		Button likeButton = new Button("Like");
		likeButton.setStyle("-fx-background-radius: 20px;");
		likeButton.setId("likeBtn");
		likeButton.setOnAction(handleButtons);
		Button commentButton = new Button("Comentar");
		commentButton.setStyle("-fx-background-radius: 20px;");
		commentButton.setId("comentarBtn");
		commentButton.setOnAction(handleButtons);
		Button seeCommentsButton = new Button("Ver comentarios");
		seeCommentsButton.setStyle("-fx-background-radius: 20px;");
		seeCommentsButton.setId("seeCommentBtn");
		seeCommentsButton.setOnAction(handleButtons);
		postHBox.getChildren().add(likeButton);
		postHBox.getChildren().add(commentButton);
		postHBox.getChildren().add(seeCommentsButton);
		postVBox.getChildren().add(postHBox);
		return postVBox;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
		nickNameLabel.setText(user.getNick_name());
	}

	public ObservableList<Node> getPostVBox() {
		return handlePostVBox;
	}
}