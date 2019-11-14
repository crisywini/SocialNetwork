package co.uniquindio.redSocial.util;

import java.io.Serializable;
import java.util.ArrayList;

import co.uniquindio.redSocial.exceptions.BigIndexException;

public class Node<T> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private T value;
	private ArrayList<Node<T>> links;

	public Node(T type) {
		value = type;
		links = new ArrayList<Node<T>>();
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public ArrayList<Node<T>> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<Node<T>> links) {
		this.links = links;
	}

	/**
	 * Metodo que permite obtener el tamanio de la lista de enlaces
	 * 
	 * @return el tamanio de enlaces del node
	 */
	public int getSizelinks() {
		return links.size();
	}

	/**
	 * Metodo que permite conectar un nodo con otro
	 * 
	 * @param destiny nodo a conectar
	 * @param index   indice donde se requiere conectar el nodo
	 */
	public void connectNode(Node<T> destiny, int index) {
		if (index >= getSizelinks()) {
			int auxiliar = index - getSizelinks();
			for (int i = 0; i < auxiliar; i++)
				links.add(null);
			links.add(destiny);
		} else
			links.set(index, destiny);
	}

	/**
	 * Metodo que permite verificar si el valor de un nodo esta conectado
	 * 
	 * @param value valor del nodo
	 * @return si el nodo esta o no conectado
	 */
	public boolean isConnected(T value) {
		int pos = getPosicionNode(value);
		return pos != -1;
	}

	/**
	 * Metodo que permite desconectar un nodo dado el indice en la lista de nodos
	 * 
	 * @param index el cual se quiere eliminar
	 * @throws BigIndexException si sobre pasa el tamanio de la lista o si es menor
	 *                           que el tamanio de la lista
	 */
	public void disconnect(int index) throws BigIndexException {
		if (index >= getSizelinks() || index < 0)
			throw new BigIndexException("The index: " + index + " is out of bounds.");
		links.set(index, null);
	}

	/**
	 * Metodo que permite obtener la posicion de un nodo dado un valor
	 * 
	 * @param value del nodo a buscar
	 * @return la posicion (si existe) del nodo con el valor correspondiente, -1 en
	 *         caso contrario
	 */
	public int getPosicionNode(T value) {
		int pos = -1;
		Node<T> current;
		boolean isOnList = false;
		for (int i = 0; i < links.size() && !isOnList; i++) {
			current = links.get(i);
			if (current != null) {
				if (current.getValue().equals(value)) {
					pos = i;
					isOnList = true;
				}
			}
		}
		return pos;
	}

	/**
	 * Metodo que permite verificar si un nodo esta conectado en un enlace
	 * especifico
	 * 
	 * @param index que permite verificar si
	 * @return un booleano con la verificacion si existe o no la conexion entre los
	 *         nodos
	 * @throws BigIndexException si el indice sobre pasa los limites
	 */
	public boolean isConnected(int index) throws BigIndexException {
		if (index >= getSizelinks() || index < 0)
			throw new BigIndexException("The index: " + index + " is out of bounds");
		return links.get(index) != null;
	}

	/**
	 * Metodo que permite seguir el enlace correspondiente segun el indice
	 * 
	 * @param index que permite seguir el enlace
	 * @return un node en el enlace
	 * @throws BigIndexException si el indice sobre pasa los limites
	 */
	public Node<T> followLink(int index) throws BigIndexException {
		if (index >= getSizelinks() || index < 0)
			throw new BigIndexException("The index: " + index + " is out of bounds");
		return links.get(index);
	}

	/**
	 * Metodo que permite obtener el indice de un nodo en el {@link ArrayList} de
	 * nodos
	 * 
	 * @param node a buscar
	 * @return -1 si no lo encuentra o >=0 si lo encuentra
	 */
	public int getIndex(Node<T> node) {
		int index = -1;
		boolean isOnLinks = false;
		Node<T> auxiliar;
		for (int i = 0; i < links.size() && !isOnLinks; i++) {
			auxiliar = links.get(i);
			if (auxiliar != null) {
				if (auxiliar.getSizelinks() > 0) {
					if (auxiliar.getValue().equals(node.getValue())) {
						index = i;
						isOnLinks = true;
					}
				}
			}
		}
		return index;
	}

	/**
	 * Metodo toString de la clase Node
	 */
	@Override
	public String toString() {
		String info = "Value: " + value + " Conexiones [";
		for (Node<T> node : links) {
			if (node == null)
				info += "null,";
			else
				info += node.getValue() + ",";
		}
		info = info.substring(0, info.length() - 1) + "]";
		if (links.size() == 0)
			info = "Value: " + value + " Conexiones []";
		return info;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		Node<T> auxiliar;
		boolean equals = false;
		if (obj instanceof Node) {
			auxiliar = (Node<T>) obj;
			if (auxiliar.getValue().equals(this.getValue()))
				equals = true;
		}
		return equals;
	}
}
