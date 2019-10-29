package co.uniquindio.redSocial.util;

import co.uniquindio.redSocial.exceptions.BigIndexException;

public class DoublyLinkedList<T> {
	private Node<T> first;
	private Node<T> last;
	private int size;

	public DoublyLinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * Metodo que permite agregar un nodo al principio de la lista
	 * 
	 * @param element del nodo
	 */
	public void addFirst(T element) {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			first = newNode;
			first.connectNode(last, 1);
		} else {
			newNode.connectNode(first, 1);
			first.connectNode(newNode, 0);
			first = newNode;
		}
		size++;
	}

	/**
	 * Metodo que permite agregar un nodo al final de la lista
	 * 
	 * @param element del nodo
	 * @throws BigIndexException
	 */
	public void addLast(T element) throws BigIndexException {
		Node<T> newNode = new Node<T>(element);
		if (isEmpty()) {
			newNode.connectNode(null, 1);
			first = newNode;
			last = newNode;
		} else {
			if (last == null) {
				Node<T> current = first;
				while (current.followLink(1) != null)
					current = current.followLink(1);
				last = current;
			}
			last.connectNode(newNode, 1);
			newNode.connectNode(last, 0);
			newNode.connectNode(null, 1);
			last = newNode;
		}
		size++;
	}

	/**
	 * Metodo que permite remover el primer elemento de la lista
	 * 
	 * @return el valor del nodo
	 * @throws IndexOutOfBoundsException
	 */
	public T removeFirst() throws BigIndexException {
		if (isEmpty())
			throw new RuntimeException("The list is empty");
		T data = first.getValue();
		if (last == first)
			first = last = null;
		else {
			first = first.followLink(1);
			first.connectNode(null, 0);
			;
		}
		size--;
		return data;
	}

	public T removeLast() throws BigIndexException {
		if (isEmpty())
			throw new RuntimeException("The list is empty");
		T data = last.getValue();
		if (last == first)
			first = last = null;
		else {
			Node<T> current = first;
			while (current.followLink(1) != last)
				current = current.followLink(1);
			last = current;
			last.connectNode(null, 1);
		}
		size--;
		return data;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public Node<T> getFirst() {
		return first;
	}

	public void setFirst(Node<T> first) {
		this.first = first;
	}

	public Node<T> getLast() {
		return last;
	}

	public void setLast(Node<T> last) {
		this.last = last;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		String info = "[";
		Node<T> current = first;
		int counter = 0;
		while (counter < size) {
			info += current.toString() + ",";
			if (current.getSizelinks() == 1)
				try {
					current = current.followLink(0);
				} catch (BigIndexException e) {
					e.printStackTrace();
				}
			else
				try {
					current = current.followLink(1);
				} catch (BigIndexException e) {
					e.printStackTrace();
				}

			counter++;
		}
		info = info.substring(0, info.length() - 1);
		info += "]";
		return info;
	}

}
