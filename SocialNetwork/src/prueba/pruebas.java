package prueba;

import co.uniquindio.redSocial.util.LinkedList;

public class pruebas {
	public static void main(String[] args) throws Exception {
		LinkedList<String> lista = new LinkedList<String>();
		lista.addLast("Liss");
	
		System.out.println(lista.toString());
		lista.remove("Liss");
		System.out.println(lista.toString());
	}
}
