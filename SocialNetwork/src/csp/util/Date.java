package csp.util;

import java.io.Serializable;
import java.util.GregorianCalendar;
public class Date implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int day;
	private int month;
	private int year;
	/**
	 * Constructor de la clase Date
	 * @param day 
	 * @param month
	 * @param year
	 */
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	/**
	 * Metodo constructor de la clase Date, inicializa los datos en hoy
	 */
	public Date() {
		this(GregorianCalendar.DATE, GregorianCalendar.MONTH, GregorianCalendar.YEAR);
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
}
