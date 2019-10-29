package co.uniquindio.redSocial.persistence;

import java.beans.XMLDecoder;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Persistencia {
	public static final String SOCIAL_NETWORK = "src/resources/SocialNetwork.xml";
	public static final String USERS = "src/resources/Users.txt";

	/**
	 * Metodo estatico que permite serializar un objeto dada una ruta
	 * 
	 * @param ruta   donde se guarda el objeto
	 * @param objeto que se desea guardar
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public static void serializarXML(String ruta, Object objeto) throws FileNotFoundException {
		FileOutputStream archivo = new FileOutputStream(ruta);
		XMLEncoder codificador = new XMLEncoder(archivo);
		codificador.writeObject(objeto);
		codificador.close();
	}

	/**
	 * Metodo estatico que permite leer la informacion de un objeto
	 * 
	 * @param ruta donde esta el archivo
	 * @return el objeto
	 * @throws FileNotFoundException si no encuentra el archivo
	 */
	public static Object deserializarObjetoXML(String ruta) throws FileNotFoundException {
		FileInputStream archivo = new FileInputStream(ruta);
		XMLDecoder decodificador = new XMLDecoder(archivo);
		Object objeto = decodificador.readObject();
		decodificador.close();
		return objeto;
	}

}