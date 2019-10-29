package co.uniquindio.redSocial.model;

import java.io.Serializable;

public class Love implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private User userAssociated;

	/**
	 * Metodo constructor con parametros de la clase Love
	 * 
	 * @param userAssociated usuario quien le dio love a algo
	 */
	public Love(User userAssociated) {
		this.userAssociated = userAssociated;
		id = "<3";
	}

	/**
	 * Metodo constructor vacio de la clase Love
	 */
	public Love() {
		this(new User());
		id = "<3*";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUserAssociated() {
		return userAssociated;
	}

	public void setUserAssociated(User userAssociated) {
		this.userAssociated = userAssociated;
	}

	@Override
	public String toString() {
		String info = "User: " + userAssociated.getName();
		return info;
	}

}
