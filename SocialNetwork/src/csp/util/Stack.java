package csp.util;

import csp.exceptions.IndexOutOfBoundsException;

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
	 * @throws RuntimeException
	 * @throws IndexOutOfBoundsException
	 */
	public T pop() throws RuntimeException, IndexOutOfBoundsException {
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
	 * @throws RuntimeException
	 */
	public Node<T> peek() throws RuntimeException {
		if (isEmpty())
			throw new RuntimeException("The list is empty");
		return linkedList.getFirst();
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
