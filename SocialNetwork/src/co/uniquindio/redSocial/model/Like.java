package co.uniquindio.redSocial.model;

import java.io.Serializable;

public class Like implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;// Verificar
	private User userAssociated;

	/**
	 * Metodo constructor de la clase Like
	 * 
	 * @param userAssociated usuario quien dio like
	 */
	public Like(User userAssociated) {
		this.userAssociated = userAssociated;
		id = "!!";
	}

	/**
	 * Metodo constructor sin parametros de la clase user
	 */
	public Like() {
		this(new User());
		id = "!!*";
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
	public boolean equals(Object obj) {
		if (obj instanceof Like) {
			Like like = (Like) obj;
			if (like.getUserAssociated().getId().equals(userAssociated.getId()))
				return true;
		}
		return false;
	}

	@Override
	public String toString() {
		String info = "User: " + userAssociated.getName();
		return info;
	}
}
