package co.uniquindio.redSocial.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Archivo {
	public static void guardarEnArchivo(String ruta, ArrayList<String> contenidoArchivo) throws IOException {
		FileWriter archivoEscritor = new FileWriter(ruta, true);
		BufferedWriter direccionadorArchivo = new BufferedWriter(archivoEscritor);
		for (String string : contenidoArchivo) {
			direccionadorArchivo.write(string);
		}
		direccionadorArchivo.flush();
		direccionadorArchivo.close();
		archivoEscritor.close();
	}

	public static ArrayList<String> leerArchivo(String ruta) throws IOException {
		ArrayList<String> contenidoArchivo = new ArrayList<String>();
		FileReader archivoLector = new FileReader(ruta);
		BufferedReader direccionadorArchivo = new BufferedReader(archivoLector);
		String linea = "";
		while ((linea = direccionadorArchivo.readLine()) != null) {
			contenidoArchivo.add(linea);
		}
		direccionadorArchivo.close();
		archivoLector.close();
		return contenidoArchivo;
	}

	public static String[] particionarString(String linea) {
		StringTokenizer tokens = new StringTokenizer(linea, "ljklkjlkj", false);
		String[] salida = new String[tokens.countTokens()];
		int cont = 0;
		while (tokens.hasMoreElements()) {
			salida[cont] = tokens.nextToken();
			cont++;
		}
		return salida;
	}

	/**
	 * @param ruta
	 * @return
	 */
	public static boolean isCreatedFile(String ruta) {
		File archivo = new File(ruta);
		return archivo.exists();
	}

}
