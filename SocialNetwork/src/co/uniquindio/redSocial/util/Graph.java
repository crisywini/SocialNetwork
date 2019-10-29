package co.uniquindio.redSocial.util;

import java.io.Serializable;
import java.util.HashMap;

import co.uniquindio.redSocial.exceptions.NodeRepeatException;

public class Graph<T> implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashMap<String, Node<T>> graph;
	private Node<T> initial;
	/**
	 * Metodo constructor sin parametros de la clase Grafo
	 */
	public Graph() {
		graph = new HashMap<String, Node<T>>();
		initial = null;
	}
	/**
	 * Metodo constructor de la clase Grafo
	 * @param initial
	 */
	public Graph(Node<T> initial) {
		graph = new HashMap<String, Node<T>>();
		this.initial = initial;
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
	 * @param name nombre del nodo
	 * @return 
	 */
	public boolean isOnGraph (String name) {
		return graph.containsKey(name);
	}
	/**
	 * Metodo para agregar un nodo al grafo
	 * @param name nombre del nodo
	 * @param value valor del nodo
	 * @throws Exception Si el nodo a agregar ya existe
	 */
	public void addNode (String name, T value) throws Exception {
		Node<T> nodo = new Node<T>(value);
		if (!isOnGraph(name)) {
			graph.put(name, nodo);
		}else {
			throw new NodeRepeatException("El nodo: " + name + " ya existe en el grafo!");
		}
	}
	
}