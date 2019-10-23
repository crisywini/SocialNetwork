package csp.model;

public class Like {

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
	public String toString() {
		String info = "User: "+userAssociated.getName();
		return info;
	}
}
