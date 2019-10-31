package prueba;

import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.util.LinkedList;

public class pruebas {
	public static void main(String[] args) throws Exception {
		LinkedList<String> lista = new LinkedList<String>();
		User usuario = new User("123", "Cris", "sanchez", "CRISI", "HOLA", "JEJE", "GAS");
		User usuario2 = new User("1223", "Criss", "sannchez", "CRISII", "HOLA", "JEJE", "GAS");
		User usuario3 = new User("1233", "CriIs", "saanchez", "CRISIi", "HOLA", "JEJE", "GAS");
		User usuario4 = new User("1235", "CrRis", "sanchez", "CRISIp", "HOLA", "JEJE", "GAS");
		User usuario5 = new User("1234", "Cris", "sanchez", "CRISIii", "HOLA", "JEJE", "GAS");
		usuario.addFriend(usuario2);
		usuario.addFriend(usuario3);
		usuario.addFriend(usuario4);
		usuario.addFriend(usuario5);
		System.out.println(usuario.getFriends().getGraph().size());
	}
}
