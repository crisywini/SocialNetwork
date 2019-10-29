package prueba;

import co.uniquindio.redSocial.util.Graph;
import co.uniquindio.redSocial.util.Node;

public class pruebas {
	public static void main(String[] args) throws Exception {
		Graph<String> grafito = new Graph<String>(new Node<String>("Initial"));
		grafito.addNode("A", "1");
		grafito.addNode("B", "2");
		grafito.addNode("C", "3");
		grafito.addNode("D", "4");
		grafito.connectWithAnotherNode("A", "B");
		System.out.println(grafito);
	}
}
