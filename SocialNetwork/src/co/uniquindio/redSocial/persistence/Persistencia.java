package co.uniquindio.redSocial.persistence;

import java.beans.XMLDecoder;

import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Persistencia {
	public static final String SOCIAL_NETWORK = "../resources/SocialNetwork.xml";
	public static final String USERS = "../resources/Users.txt";
	public static final String SOCIAL_NETWORK_DAT = "src/co/uniquindio/redSocial/resources/SocialNetwork.dat";

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

	/**
	 * Metodo que permite serializar un objeto en un archivo binario
	 * 
	 * @param ruta   nombre de la ubicacion del archivo
	 * @param objeto el cual se va a guardar en un archivo binario
	 * @throws IOException si ocurre algun problema de entrada y/o salida de datos
	 */
	public static void serializarObjeto(String ruta, Object objeto) throws IOException {
		FileOutputStream out = new FileOutputStream(ruta);
		ObjectOutputStream serial = new ObjectOutputStream(out);
		serial.writeObject(objeto);
		serial.flush();
		serial.close();
	}

	/**
	 * Metodo que permite leer un archivo serializado en binario
	 * 
	 * @param ruta nombre de la ubicacion del archivo que se requiere leer
	 * @return el objeto leido del archivo binario
	 * @throws IOException            si ocurre algun problema de entrada y/o salida
	 *                                de datos
	 * @throws ClassNotFoundException si no se puede leer el objeto del archivo
	 *                                serializado
	 */
	public static Object deserializarObjeto(String ruta) throws IOException, ClassNotFoundException {
		FileInputStream in = new FileInputStream(ruta);
		ObjectInputStream deserial = new ObjectInputStream(in);
		Object objeto = deserial.readObject();
		deserial.close();
		return objeto;
	}

}