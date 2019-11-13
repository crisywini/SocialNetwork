package co.uniquindio.redSocial.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import co.uniquindio.redSocial.exceptions.*;
import co.uniquindio.redSocial.util.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String nick_name;
	private String email;
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
	public User(String name, String surname, String nick_name, String email) {
		this.name = name;
		this.surname = surname;
		this.nick_name = nick_name;
		this.email = email;
		setFriends(new Graph<User>());
		myWall = new Wall(this);
		friendsRequest = new LinkedList<User>();
		blockedFriends = new LinkedList<User>();
		mails = new Stack<Mail>();
		try {
			friends.addNode(getNick_name(), this);
		} catch (NodeRepeatException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo constructor vacio de la clase user
	 */
	public User() {
		this("#$#$#$#$#$", "Nicknull", "@@@", "##$##");
	}

	public String getName() {
		return name;
	}

	public StringProperty nameProperty() {
		return new SimpleStringProperty(getName());
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

	public StringProperty nick_NameProperty() {
		return new SimpleStringProperty(getNick_name());
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
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

	public LinkedList<User> getFriendsRequest() {
		return friendsRequest;
	}

	public void setFriendsRequest(LinkedList<User> friendsRequest) {
		this.friendsRequest = friendsRequest;
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
	 * Metodo que permite enviar una solicitud de amistad a un usuario
	 * 
	 * @param user a quien se le enviara una solicitud de amistad
	 * @throws BigIndexException   de la clase lista
	 * @throws NodeRepeatException si el usuario ya esta en la lista de solicitudes
	 *                             de amistad
	 */
	public void sendRequest(User user) throws BigIndexException, NodeRepeatException {
		if (user.getFriendsRequest().getSize() == 0) {
			user.getFriendsRequest().addLast(this);
			return;
		}
		if (!user.getFriendsRequest().isOnList(this)) {
			user.getFriendsRequest().addLast(this);
		} else
			throw new NodeRepeatException("Ya enviaste la solicitud de amistad a: " + user.getNick_name());
	}

	/**
	 * Metodo para aceptar una solicitud de amistad
	 * 
	 * @param friend amigo solicitante
	 * @throws BigIndexException      de la clase nodo
	 * @throws NodeRepeatException    de la clase LinkedList
	 * @throws NotFriendsException    si no tienes solicitud de amistad de ese amigo
	 * @throws NodeGraphNullException
	 */
	public void acceptRequest(User friend)
			throws BigIndexException, NodeRepeatException, NotFriendsException, NodeGraphNullException {
		if (friendsRequest.isOnList(friend)) {
			friends.addNode(friend.getNick_name(), friend);
			friends.connectWithAnotherNode(nick_name, friend.getNick_name());
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
		if (blockedFriends.isEmpty()) {
			blockedFriends.addFirst(blockFriend);
			friends.disconnect(nick_name, blockFriend.getNick_name());
		} else {
			if (!blockedFriends.isOnList(blockFriend)) {
				blockedFriends.addFirst(blockFriend);
				friends.disconnect(nick_name, blockFriend.getNick_name());
			}
		}
	}

	/**
	 * Metodo que permite desbloquear un amigo bloqueado
	 * 
	 * @param friend amigo a ser desbloqueado
	 * @throws BigIndexException        de la clase nodo
	 * @throws NodeRepeatException      de la clase {@link LinkedList}
	 * @throws UnblockedFriendException si la persona no se encuentra en la lista de
	 *                                  amigos bloqueados
	 * @throws NodeGraphNullException
	 */
	public void unblockFriend(User friend)
			throws BigIndexException, NodeRepeatException, UnblockedFriendException, NodeGraphNullException {
		if (blockedFriends.isEmpty())
			return;
		if (blockedFriends.isOnList(friend)) {
			blockedFriends.remove(friend);
			friends.connectWithAnotherNode(nick_name, friend.getNick_name());
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

	/**
	 * Metodo que permite eliminar a un amigo del grafo de amigos
	 * 
	 * @param friend a ser eliminado
	 * @throws NodeGraphWithLinksException de la clase Graph
	 * @throws NodeGraphNullException      de la clase Graph
	 */
	public void removeFriend(User friend) throws NodeGraphWithLinksException, NodeGraphNullException {
		if (!friend.getNick_name().equals(nick_name)) {
			friends.remove(friend.getNick_name());
		}
	}

	/**
	 * Metodo que permite eliminar un usuario de la lista de solicitudes de amistad
	 * 
	 * @param user a ser eliminado de las solicitudes de amistad
	 * @throws BigIndexException de la clase Node
	 */
	public void removeRequest(User user) throws BigIndexException {
		friendsRequest.remove(user);
	}

	/**
	 * Metodo que permite obtener un {@link ArrayList} con los mails
	 * 
	 * @return un {@link ArrayList}
	 * @throws EmptyLinkedListException
	 * @throws BigIndexException
	 */
	public ArrayList<Mail> getMailsArrayList() throws EmptyLinkedListException, BigIndexException {
		ArrayList<Mail> mails = new ArrayList<Mail>();
		Node<Mail> node = getMails().peek();
		while (node != null) {
			if (node.getLinks().size() != 0) {
				node = node.followLink(0);
				mails.add(node.getValue());
			} else
				break;
		}
		return mails;
	}

	/**
	 * Metodo que permite agregar el grafo de amigos a un {@link ArrayList}
	 * 
	 * @return {@link ArrayList}
	 */
	public ArrayList<User> friendsInArrayList() {
		ArrayList<User> friends = new ArrayList<User>();
		HashMap<String, Node<User>> friendsGraph = getFriends().getGraph();
		String nick_name = "";
		Node<User> auxiliarFriend;
		Iterator<String> nick_nameFriends = friendsGraph.keySet().iterator();
		while (nick_nameFriends.hasNext()) {
			nick_name = nick_nameFriends.next();
			auxiliarFriend = friendsGraph.get(nick_name);
			friends.add(auxiliarFriend.getValue());
		}
		return friends;
	}

	/**
	 * Metodo que permite agregar a un {@link ArrayList} las solicitudes de amistad
	 * 
	 * @return un {@link ArrayList} con la informacion de las solicitudes de amistad
	 * @throws BigIndexException de la clase {@link Node}
	 */
	public ArrayList<User> getRequestedFriendsInList() throws BigIndexException {
		ArrayList<User> requestedFriends = new ArrayList<User>();
		Node<User> current = getFriendsRequest().getFirst();
		while (current != null) {
			requestedFriends.add(current.getValue());
			if (current.getLinks().size() > 0)
				current = current.followLink(0);
			else
				break;
		}
		return requestedFriends;
	}

	/**
	 * Metodo que permite obtener un {@link ArrayList} con la informacion de los
	 * usuarios bloqueados
	 * 
	 * @return un {@link ArrayList} con la informacion de los usuarios bloqueados
	 * @throws BigIndexException de la clase nodo
	 */
	public ArrayList<User> getBlockedFriendsInList() throws BigIndexException {
		ArrayList<User> blockedFriends = new ArrayList<User>();
		Node<User> current = getBlockedFriends().getFirst();
		while (current != null) {
			blockedFriends.add(current.getValue());
			if (current.getLinks().size() > 0)
				current = current.followLink(0);
			else
				break;
		}
		return blockedFriends;
	}

	@Override
	public boolean equals(Object obj) {
		boolean isEqual = false;
		User auxiliar;
		if (obj instanceof User) {
			auxiliar = (User) obj;
			if (auxiliar.getNick_name().equals(this.getNick_name()))
				isEqual = true;
		}
		return isEqual;
	}

}