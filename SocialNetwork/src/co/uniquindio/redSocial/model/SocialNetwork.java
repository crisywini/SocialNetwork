package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeGraphWithLinksException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.util.Graph;

/**
 * Clase principal de la logica
 * 
 * @author Lissette Quebrada Lancheros,
 * @author Luisa Fernanda Cotte Sanchez,
 * @author Cristian Giovanny Sanchez Pineda
 */
public class SocialNetwork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Graph<User> users;
	private String name;// Criluli

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

	/**
	 * Metodo que permite agregar un usuario
	 * 
	 * @param id        del usuario
	 * @param name      del usuario
	 * @param surname   del usuario
	 * @param nick_name del usuario
	 * @param email     del usuario
	 * @param password  del usuario
	 * @param image     del usuario
	 * @throws NodeRepeatException de la clase Grafo
	 */
	public void addUser(String name, String surname, String nick_name, String email, String image)
			throws NodeRepeatException {
		users.addNode(nick_name, new User(name, surname, nick_name, email, image));
	}

	/**
	 * Metodo que permite eliminar un usuario
	 * 
	 * @param nick_name del usuario
	 * @return la informacion del usuario eliminado
	 * @throws NodeGraphWithLinksException de la clase grafo
	 * @throws NodeGraphNullException      de la clase grafo
	 */
	public User removeUser(String nick_name) throws NodeGraphWithLinksException, NodeGraphNullException {
		return users.remove(nick_name).getValue();
	}
}
