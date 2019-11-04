package co.uniquindio.redSocial.util;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Iterator;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeGraphWithLinksException;
import co.uniquindio.redSocial.exceptions.NodeNotConnectedException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;

public class Graph<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Node<T>> graph;
	private Node<T> initial;
	private int size;

	/**
	 * Metodo constructor sin parametros de la clase Grafo
	 */
	public Graph() {
		graph = new HashMap<String, Node<T>>();
		initial = null;
		setSize(graph.size());
	}

	/**
	 * Metodo constructor de la clase Grafo
	 * 
	 * @param initial
	 */
	public Graph(Node<T> initial) {
		graph = new HashMap<String, Node<T>>();
		this.initial = initial;
		setSize(graph.size());
	}

	public HashMap<String, Node<T>> getGraph() {
		return graph;
	}

	public void setGraph(HashMap<String, Node<T>> graph) {
		this.graph = graph;
	}

	public Node<T> getInitial() {
		return initial;
	}

	public void setInitial(Node<T> initial) {
		this.initial = initial;
	}

	/**
	 * Metodo para verificar si un nodo esta en el grafo
	 * 
	 * @param name nombre del nodo
	 * @return
	 */
	public boolean isOnGraph(String name) {
		return graph.containsKey(name);
	}

	/**
	 * Metodo para agregar un nodo al grafo
	 * 
	 * @param name  nombre del nodo
	 * @param value valor del nodo
	 * @throws NodeRepeatException Si el nodo a agregar ya existe
	 */
	public void addNode(String name, T value) throws NodeRepeatException {
		Node<T> nodo = new Node<T>(value);
		if (!isOnGraph(name)) {
			graph.put(name, nodo);
			size++;
		} else {
			throw new NodeRepeatException("El nodo: " + name + " ya existe en el grafo!");
		}
	}

	/**
	 * Metodo que permite remover un nodo
	 * 
	 * @param name del nodo
	 * @return la informacion del nodo removido
	 * @throws NodeGraphWithLinksException si el nodo tiene alguna conexion
	 * @throws NodeGraphNullException      si el nodo no existe
	 */
	public Node<T> remove(String name) throws NodeGraphWithLinksException, NodeGraphNullException {
		Node<T> auxiliar;
		if (!isOnGraph(name))
			throw new NodeGraphNullException("El nodo: " + name + " no se encuentra en el grafo");

		auxiliar = graph.get(name);
		if (auxiliar.getLinks().size() > 0)
			throw new NodeGraphWithLinksException(
					"El nodo: " + name + " no puede ser eliminado ya que tiene mas conexiones");
		size--;
		return graph.remove(name);
	}

	/**
	 * Metodo que permite desconectar un nodo con otro
	 * 
	 * @param originName  nodo que quiere ser desconectado
	 * @param destinyName nodo que se quiere desconectar
	 * @throws NodeGraphNullException
	 * @throws NodeNotConnectedException
	 * @throws BigIndexException
	 */
	public void disconnect(String originName, String destinyName)
			throws NodeGraphNullException, NodeNotConnectedException, BigIndexException {
		if (!isOnGraph(originName) || !isOnGraph(destinyName))
			throw new NodeGraphNullException(
					"El nodo: " + originName + " o el nodo: " + destinyName + " no se encuentran en el grafo");
		Node<T> auxiliarOrgin = graph.get(originName);
		Node<T> auxiliarDestiny = graph.get(destinyName);
		int destinyIndex = auxiliarOrgin.getIndex(auxiliarDestiny);
		if (destinyIndex == -1)
			throw new NodeNotConnectedException("El nodo: " + destinyName + " no esta conectado con: " + originName);
		auxiliarOrgin.disconnect(destinyIndex);
	}

	/**
	 * Metodo que permite conectar un nodo con otro
	 * 
	 * @param originName  nodo origen
	 * @param destinyName nodo destino
	 * @throws NodeGraphNullException si el nodo no existe
	 */
	public void connectWithAnotherNode(String originName, String destinyName) throws NodeGraphNullException {
		Node<T> origin = graph.get(originName);
		Node<T> destiny = graph.get(destinyName);
		if (origin == null || destiny == null)
			throw new NodeGraphNullException(
					"El nodo: " + originName + " o el nodo: " + destinyName + " no existen en el grafo");
		int availableOrigin = getAvailableIndex(originName);
		int availableDestiny = getAvailableIndex(destinyName);
		origin.connectNode(destiny, availableOrigin);
		destiny.connectNode(origin, availableDestiny);
	}

	/**
	 * Metodo que permite obtener el primer enlace libre de un nodo
	 * 
	 * @param name nombre del nodo
	 * @return el indice en el cual exista un espacio libre
	 * @throws NodeGraphNullException si el nodo no existe
	 */
	public int getAvailableIndex(String name) throws NodeGraphNullException {
		if (!graph.containsKey(name))
			throw new NodeGraphNullException("El nodo: " + name + " no existe en el grafo");
		Node<T> auxiliar = graph.get(name);
		int index = -1;
		boolean isAvailable = false;
		for (int i = 0; i < auxiliar.getLinks().size() && !isAvailable; i++) {
			if (auxiliar.getLinks().get(i) == null) {
				index = i;
				isAvailable = true;
			}
		}
		if (auxiliar.getLinks().size() == 0)
			index = 0;
		else if (index == -1)
			index = auxiliar.getLinks().size();
		return index;
	}

	/**
	 * Metodo que permite seguir el enlace de un nodo
	 * 
	 * @param name  del nodo
	 * @param index del enlace
	 * @return el nodo segun el indice
	 * @throws NodeGraphNullException si el nodo no existe
	 * @throws BigIndexException
	 */
	public Node<T> followLink(String name, int index) throws NodeGraphNullException, BigIndexException {
		if (!isOnGraph(name))
			throw new NodeGraphNullException("El nodo: " + name + " no se encuentra en el grafo");
		return graph.get(name).followLink(index);
	}

	/**
	 * Metodo que permite almacenar un dato en un nodo del grafo
	 * 
	 * @param name  del nodo
	 * @param value a almacenar
	 * @throws NodeGraphNullException si el nodo no existe
	 */
	public void setValue(String name, T value) throws NodeGraphNullException {
		if (!isOnGraph(name))
			throw new NodeGraphNullException("El nodo: " + name + " no se encuentra en el grafo");
		graph.get(name).setValue(value);
	}

	@Override
	public String toString() {
		String info = "[";
		Iterator<String> iterator = graph.keySet().iterator();
		while (iterator.hasNext()) {
			info += graph.get(iterator.next()).toString() + ",";
		}
		info = info.substring(0, info.length() - 1) + "]";
		return info;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}