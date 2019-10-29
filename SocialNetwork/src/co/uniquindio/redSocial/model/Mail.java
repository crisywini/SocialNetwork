package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.util.Date;

public class Mail implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private User transmitter;
	private User receiver;
	private String message;
	private Date date;

	/**
	 * Metodo constructor vacio de la clase Mail
	 */
	public Mail() {
		setDate(new Date());
		setId("#$#$#$");
		setTransmitter(new User());
		setReceiver(new User());
		setMessage("%&%&%");
	}

	/**
	 * Metodo constructor con paramtros de la clase Mail
	 * 
	 * @param message     del mail
	 * @param transmitter quien hizo el mail
	 * @param receiver    quien va a recibir el mail
	 */
	public Mail(String message, User transmitter, User receiver) {
		this.message = message;
		this.transmitter = transmitter;
		this.receiver = receiver;
		date = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getTransmitter() {
		return transmitter;
	}

	public void setTransmitter(User transmitter) {
		this.transmitter = transmitter;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
