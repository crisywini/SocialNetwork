package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.util.Stack;

public class Wall implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Stack<Post> publications;
	private User userAssociated;

	/**
	 * Metodo constructor sin parametros de la clase Wall
	 */
	public Wall() {
		this(new User());
	}

	/**
	 * Metodo constructor con parametros de la clase Wall
	 * 
	 * @param userAssociated el cual le pertenece el Wall
	 */
	public Wall(User userAssociated) {
		publications = new Stack<Post>();
		this.userAssociated = userAssociated;

	}

	public Stack<Post> getPublications() {
		return publications;
	}

	public void setPublications(Stack<Post> publications) {
		this.publications = publications;
	}

	public User getUserAssociated() {
		return userAssociated;
	}

	public void setUserAssociated(User userAssociated) {
		this.userAssociated = userAssociated;
	}
	@Override
	public String toString() {
		return getPublications()+"";
	}

}
