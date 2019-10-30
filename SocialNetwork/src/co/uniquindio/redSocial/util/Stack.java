package co.uniquindio.redSocial.util;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.EmptyLinkedListException;

public class Stack<T> {
	private LinkedList<T> linkedList;

	/**
	 * Metodo constructor de la clase Stack
	 */
	public Stack() {
		linkedList = new LinkedList<T>();
	}

	/**
	 * Metodo que permite agregar un elemento en la pila
	 * 
	 * @param element
	 */
	public void push(T element) {
		linkedList.addFirst(element);
	}

	/**
	 * Metodo que permite eliminar un elemento de la pila
	 * 
	 * @return la informacion del elemento eliminado
	 * @throws EmptyLinkedListException
	 * @throws BigIndexException
	 */
	public T pop() throws EmptyLinkedListException, BigIndexException {
		return linkedList.removeFirst();
	}

	/**
	 * Metodo que permite verificar si la pila esta vacia
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	/**
	 * Metodo que permite obtener el primer elemento de la pila
	 * 
	 * @return el primer elemento de la lista
	 * @throws EmptyLinkedListException
	 * @throws RuntimeException
	 */
	public Node<T> peek() throws EmptyLinkedListException {
		if (isEmpty())
			throw new EmptyLinkedListException("La pila esta vacia");
		return linkedList.getFirst();
	}

	/**
	 * Metodo que permite verificar si un nodo esta en la pila
	 * 
	 * @param element
	 * @return
	 * @throws EmptyLinkedListException
	 * @throws BigIndexException
	 */
	public boolean contains(Node<T> element) throws BigIndexException, EmptyLinkedListException {
		Node<T> auxiliar = peek();
		boolean contains = false;
		while (auxiliar != null) {
			if (auxiliar.getSizelinks() == 0)
				auxiliar = null;
			else
				try {
					auxiliar = auxiliar.followLink(0);
					if (auxiliar.equals(element))
						contains = true;
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
		}
		return contains;
	}

	public LinkedList<T> getLinkedList() {
		return linkedList;
	}

	public void setLinkedList(LinkedList<T> linkedList) {
		this.linkedList = linkedList;
	}

	@Override
	public String toString() {
		return linkedList.toString();
	}
}
