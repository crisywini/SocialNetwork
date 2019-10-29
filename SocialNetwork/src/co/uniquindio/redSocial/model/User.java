package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.util.Graph;

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
	/**
	 * Metodo que permite agregar un amigo
	 * @param newFriend
	 * @throws NodeRepeatException
	 */
	public void addFriend(User newFriend) throws NodeRepeatException {
		getFriends().addNode(newFriend.id, newFriend);
	}
	
	public void blockFriend(User blockFriend)
	{
		
	}
}