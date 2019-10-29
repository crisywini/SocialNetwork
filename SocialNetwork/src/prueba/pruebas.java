package prueba;

import co.uniquindio.redSocial.util.Graph;
import co.uniquindio.redSocial.util.Node;

public class pruebas {
	public static void main(String[] args) throws Exception {
		Graph<String> grafito = new Graph<String>(new Node<String>("Initial"));
		grafito.addNode("Crisi", "321321");
		grafito.addNode("321", "NIco");
		grafito.connectWithAnotherNode("Crisi", "321");
		System.out.println(grafito);
	}
}
