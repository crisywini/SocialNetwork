package csp.exceptions;

import java.io.Serializable;

public class IndexOutOfBoundsException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IndexOutOfBoundsException(String errorMessage) {
		super(errorMessage);
	}
}
