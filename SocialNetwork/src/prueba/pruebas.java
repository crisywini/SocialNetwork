package prueba;

import co.uniquindio.redSocial.exceptions.EmptyLinkedListException;

import co.uniquindio.redSocial.exceptions.IndexOutOfBoundsException;
import co.uniquindio.redSocial.exceptions.LikeNullException;
import co.uniquindio.redSocial.model.Post;
import co.uniquindio.redSocial.model.User;


public class pruebas {
	public static void main(String[] args) throws IndexOutOfBoundsException, EmptyLinkedListException, LikeNullException {
		User crisi = new User();
		crisi.setName("Crisi");
		User nico = new User();
		nico.setName("Nico");
		Post miPost = new Post("Hola", crisi);	
		System.out.println(miPost);
	}
}
