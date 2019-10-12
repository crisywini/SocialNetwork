package csp.util;

import csp.exceptions.IndexOutOfBoundsException;

public class Queue<T> {
	private LinkedList<T> linkedList;

	public Queue() {
		linkedList = new LinkedList<T>();
	}

	public void add(T element) {
		linkedList.addLast(element);
	}

	public T poll() throws RuntimeException, IndexOutOfBoundsException {
		return linkedList.removeFirst();
	}

	public Node<T> peek() throws RuntimeException {
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
