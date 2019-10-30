package co.uniquindio.redSocial.util;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.EmptyLinkedListException;

public class Queue<T> {
	private LinkedList<T> linkedList;

	public Queue() {
		linkedList = new LinkedList<T>();
	}

	public void add(T element) {
		linkedList.addLast(element);
	}

	public T poll() throws EmptyLinkedListException, BigIndexException {
		return linkedList.removeFirst();
	}

	public Node<T> peek() {
		return linkedList.getFirst();
	}

	public boolean isEmpty() {
		return linkedList.isEmpty();
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
