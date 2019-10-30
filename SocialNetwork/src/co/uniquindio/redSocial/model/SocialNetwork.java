package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.util.Graph;

/**
 * Clase principal de la logica
 * 
 * @author Lissette Quebrada Lancheros, Luisa Fernanda Cotte Sanchez, 
 *         Cristian Giovanny Sanchez Pineda
 *
 */
public class SocialNetwork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graph<User> users;
	private String name;

	/**
	 * Metodo constructor de la clase {@link SocialNetwork} sin parametros
	 */
	public SocialNetwork() {
		this("#$#$");
	}

	/**
	 * Metodo constructor con parametros de la clase {@link SocialNetwork}
	 * 
	 * @param name de la red social
	 */
	public SocialNetwork(String name) {
		this.name = name;
		users = new Graph<User>();
	}

	public Graph<User> getUsers() {
		return users;
	}

	public void setUsers(Graph<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
