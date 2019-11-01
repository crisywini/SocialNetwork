package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.exceptions.*;
import co.uniquindio.redSocial.util.*;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String nick_name;
	private String email;
	private String image;
	private Graph<User> friends;
	private Wall myWall;
	private LinkedList<User> blockedFriends;
	private Stack<Mail> mails;
	private LinkedList<User> friendsRequest;
	public static final int MAX_FRIENDS = 100;

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
	public User(String name, String surname, String nick_name, String email, String image) {
		this.name = name;
		this.surname = surname;
		this.nick_name = nick_name;
		this.email = email;
		this.image = image;
		setFriends(new Graph<User>());
		myWall = new Wall(this);
		friendsRequest = new LinkedList<User>();
	}

	/**
	 * Metodo constructor vacio de la clase user
	 */
	public User() {
		this("#$#$#$#$#$", "Nicknull", "@@@", "##$##", "URL");
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
	 * @param newFriend nuevo amigo
	 * @throws NodeRepeatException       de la clase nodo
	 * @throws MaxNumberFriendsException si supera la cantidad de amigos predicha
	 */
	public void addFriend(User newFriend) throws NodeRepeatException, MaxNumberFriendsException {
		if (getFriends().getGraph().size() < MAX_FRIENDS)
			getFriends().addNode(newFriend.getNick_name(), newFriend);
		else
			throw new MaxNumberFriendsException("Superas la cantidad de amigos, no puedes tener mas!");

	}

	/**
	 * Metodo para aceptar una solicitud de amistad
	 * 
	 * @param friend amigo solicitante
	 * @throws BigIndexException   de la clase nodo
	 * @throws NodeRepeatException de la clase LinkedList
	 * @throws NotFriendsException si no tienes solicitud de amistad de ese amigo
	 */
	public void acceptRequest(User friend) throws BigIndexException, NodeRepeatException, NotFriendsException {
		if (friendsRequest.isOnList(friend)) {
			friends.addNode(friend.getNick_name(), friend);
			friendsRequest.remove(friend);
		} else {
			throw new NotFriendsException("No tienes una solicitud de amistad de: " + friend.getNick_name());
		}
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

	/**
	 * Metodo que permite desbloquear un amigo bloqueado
	 * 
	 * @param friend amigo a ser desbloqueado
	 * @throws BigIndexException        de la clase nodo
	 * @throws NodeRepeatException      de la clase {@link LinkedList}
	 * @throws UnblockedFriendException si la persona no se encuentra en la lista de
	 *                                  amigos bloqueados
	 */
	public void unblockFriend(User friend) throws BigIndexException, NodeRepeatException, UnblockedFriendException {
		if (blockedFriends.isOnList(friend)) {
			blockedFriends.remove(friend);
			friends.addNode(friend.getNick_name(), friend);
		} else
			throw new UnblockedFriendException("No tienes el usuario: " + friend.getNick_name() + " bloqueado!");

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
			if (auxiliar.getNick_name().equals(this.getNick_name()))
				isEquals = true;
		}
		return isEquals;
	}

}