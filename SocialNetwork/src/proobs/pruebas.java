package proobs;

import csp.exceptions.EmptyLinkedListException;
import csp.exceptions.IndexOutOfBoundsException;
import csp.exceptions.LikeNullException;
import csp.model.Post;
import csp.model.User;
import csp.util.Node;
import csp.util.Stack;

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
