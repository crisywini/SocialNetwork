package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.BlockedFriendException;
import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeNotConnectedException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.exceptions.NotFriendsException;
import co.uniquindio.redSocial.util.Graph;
import co.uniquindio.redSocial.util.LinkedList;
import co.uniquindio.redSocial.util.Node;
import co.uniquindio.redSocial.util.Stack;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;
	private String surname;
	private String nick_name;
	private String email;
	private String password;
	private String image;
	private Graph<User> friends;
	private Wall myWall;
	private LinkedList<User> blockedFriends;
	private Stack<Mail> mails;

	/**
	 * Metodo constructor de la clase user
	 * 
	 * @param id        del user
	 * @param name      del user
	 * @param surname   del user
	 * @param nick_name del user
	 * @param email     del user
	 * @param password  del user
	 * @param image     url del user para su foto
	 */
	public User(String id, String name, String surname, String nick_name, String email, String password, String image) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.nick_name = nick_name;
		this.email = email;
		this.password = password;
		this.image = image;
		setFriends(new Graph<User>());
		myWall = new Wall(this);
	}

	/**
	 * Metodo constructor vacio de la clase user
	 */
	public User() {
		this("$%$%$%$", "#$#$#$#$#$", "&#&#&#&#&", "Nicknull", "@@@", "##$##", "URL");
	}

	public String getId() {
		return id;
	}

	public StringProperty idProperty() {
		return new SimpleStringProperty(id);
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Wall getMyWall() {
		return myWall;
	}

	public void setMyWall(Wall myWall) {
		this.myWall = myWall;
	}

	public Graph<User> getFriends() {
		return friends;
	}

	public void setFriends(Graph<User> friends) {
		this.friends = friends;
	}

	public LinkedList<User> getBlockedFriends() {
		return blockedFriends;
	}

	public void setBlockedFriends(LinkedList<User> blockedFriends) {
		this.blockedFriends = blockedFriends;
	}

	public Stack<Mail> getMails() {
		return mails;
	}

	public void setMails(Stack<Mail> mails) {
		this.mails = mails;
	}

	/**
	 * Metodo que permite agregar un amigo
	 * 
	 * @param newFriend
	 * @throws NodeRepeatException
	 */
	public void addFriend(User newFriend) throws NodeRepeatException {
		getFriends().addNode(newFriend.getNick_name(), newFriend);
	}

	/**
	 * Metodo que permite bloquear un amigo
	 * 
	 * @param blockFriend amigo a ser bloqueado
	 * @throws BigIndexException         de la clase nodo
	 * @throws NodeNotConnectedException de la clase nodo
	 * @throws NodeGraphNullException    de la clase grafo
	 */
	public void blockFriend(User blockFriend)
			throws NodeGraphNullException, NodeNotConnectedException, BigIndexException {
		blockedFriends.addFirst(blockFriend);
		friends.disconnect(nick_name, blockFriend.getNick_name());
	}
	
	public void unblockFriend (User friend) throws BigIndexException, NodeRepeatException {
		blockedFriends.remove(friend);
		friends.addNode(friend.getNick_name(), friend);
	}

	/**
	 * Metodo que permite enviar un mensaje a un amigo
	 * 
	 * @param friend  receptor
	 * @param message contenido del mail
	 * @throws NotFriendsException    si no son amigos
	 * @throws BlockedFriendException si esta bloqueado el amigo
	 * @throws BigIndexException      de la clase LinkedList
	 */
	public void sendMessage(User friend, String message)
			throws NotFriendsException, BlockedFriendException, BigIndexException {
		if (!friends.isOnGraph(friend.getNick_name()))
			throw new NotFriendsException("La persona: " + friend.getNick_name() + " y tu no son amigos");
		if (blockedFriends.isOnList(friend))
			throw new BlockedFriendException("La persona: " + friend.getNick_name() + " esta bloqueada");
		Mail newMail = new Mail(message, this, friend);
		this.mails.push(newMail);
		friend.mails.push(newMail);
	}

	/**
	 * Metodo que envía mensaje a mas de un amigo
	 * 
	 * @param receivers listado de amigos a enviar el mensaje
	 * @param message   contenido
	 * @throws NotFriendsException    si no son amigos
	 * @throws BigIndexException      es de la clase LinkedList
	 * @throws BlockedFriendException si esta bloqueado
	 */
	public void sendMessage(LinkedList<User> receivers, String message)
			throws NotFriendsException, BigIndexException, BlockedFriendException {

		Node<User> nodeUserAuxiliar = receivers.getFirst();
		User auxiliar = nodeUserAuxiliar.getValue();

		while (nodeUserAuxiliar != null) {
			if (!friends.isOnGraph(auxiliar.getNick_name())) {
				throw new NotFriendsException("La persona: " + auxiliar.getNick_name() + " y tu no son amigos");
			}
			if (blockedFriends.isOnList(auxiliar)) {
				throw new BlockedFriendException("La persona: " + auxiliar.getNick_name() + " esta bloqueada");
			}

			Mail newMail = new Mail(message, this, auxiliar);
			this.mails.push(newMail);
			auxiliar.mails.push(newMail);
			nodeUserAuxiliar = nodeUserAuxiliar.followLink(0);
			auxiliar = nodeUserAuxiliar.getValue();
		}

	}
	

	@Override
	public boolean equals(Object obj) {
		boolean isEquals = false;
		User auxiliar;
		if (obj instanceof User) {
			auxiliar = (User) obj;
			if (auxiliar.getId().equals(this.getId()) && auxiliar.getNick_name().equals(this.getNick_name()))
				isEquals = true;
		}
		return isEquals;
	}

}