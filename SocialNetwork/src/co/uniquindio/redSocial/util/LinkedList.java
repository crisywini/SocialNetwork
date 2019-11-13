package co.uniquindio.redSocial.util;

import java.io.Serializable;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.EmptyLinkedListException;

public class LinkedList<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Node<T> first;
	private Node<T> last;
	private int size;

	public LinkedList() {
		first = null;
		last = null;
		size = 0;
	}

	/**
	 * Metodo que permite agreagr un nodo al principio de la lista
	 * 
	 * @param element
	 */
	public void addFirst(T element) {
		if (isEmpty()) {
			first = last = new Node<T>(element);
		} else {
			Node<T> auxiliar = new Node<T>(element);
			auxiliar.connectNode(first, 0);
			first = auxiliar;
		}
		size++;
	}

	/**
	 * Metodo que permite agregar un nodo al final de la lista
	 * 
	 * @param element
	 */
	public void addLast(T element) {
		if (isEmpty()) {
			first = last = new Node<T>(element);
		} else {
			Node<T> newNode = new Node<T>(element);
			last.connectNode(newNode, 0);
			last = newNode;
		}
		size++;
	}

	/**
	 * Metodo que permite eliminar el primer nodo de la lista
	 * 
	 * @return la informacion del nodo
	 * @throws EmptyLinkedListException
	 * @throws BigIndexException
	 */
	public T removeFirst() throws EmptyLinkedListException, BigIndexException {
		if (isEmpty())
			throw new EmptyLinkedListException("The list is empty");
		T data = first.getValue();
		if (first == last)
			first = last = null;
		else
			first = first.followLink(0);
		size--;
		return data;
	}

	public T removeLast() throws EmptyLinkedListException, BigIndexException {
		if (isEmpty())
			throw new EmptyLinkedListException("The list is empty");
		T data = last.getValue();
		if (last == first)
			first = last = null;
		else {
			Node<T> current = first;
			while (current.followLink(0) != last) {
				current = current.followLink(0);
			}
			last = current;
			current.connectNode(null, 0);
		}
		size--;
		return data;
	}

	/**
	 * Metodo que permite verificar si existe un elemento en la lista
	 * 
	 * @param value
	 * @return
	 * @throws BigIndexException
	 */
	public boolean isOnList(T value) throws BigIndexException {
		boolean exist = false;
		Node<T> auxiliar = getFirst();
		while (auxiliar != null && !exist) {
			if (auxiliar.getValue().equals(value))
				exist = true;
			if (auxiliar.getLinks().size() > 0) {
				auxiliar = auxiliar.followLink(0);
			} else
				break;
		}
		return exist;
	}

	/**
	 * Metodo que permite eliminar un elemento de la lista
	 * 
	 * @param value valor a eliminar
	 * @throws BigIndexException deborde
	 */
	public void remove(T value) throws BigIndexException {
		Node<T> node = first;
		if (getSize() == 1 && node.getValue().equals(value)) {
			first = null;
		} else if (node.getValue().equals(value) && first.getLinks().size() > 1) {
			first = first.followLink(0);
			node = null;
		} else {
			while (node != null) {
				if (node.followLink(0).getValue().equals(value)) {
					Node<T> aux = node.followLink(0);
					node.connectNode(aux.followLink(0), 0);
					break;
				}
				if (node.getLinks().size() > 0) {
					node = node.followLink(0);
				} else
					break;
			}
		}

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
		if (isEmpty())
			info = "The list is empty";
		else {
			Node<T> current = first;
			while (current != null) {
				info += current.getValue() + ",";
				if (current.getSizelinks() == 0)
					current = null;
				else
					try {
						current = current.followLink(0);
					} catch (BigIndexException e) {
						e.printStackTrace();
					}
			}
			info = info.substring(0, info.length() - 1) + "]";
		}
		return info;
	}
}