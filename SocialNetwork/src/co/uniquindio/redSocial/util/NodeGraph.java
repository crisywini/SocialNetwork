package co.uniquindio.redSocial.util;

import java.util.ArrayList;

public class NodeGraph <T>{
	private T value;
	private ArrayList<Node<T>> links;
	
	
	public NodeGraph(T value) {
		this.value = value;
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
	public void connect(Node<T> destiny, int index)
	{
		if(index>= links.size())
		{
			int number = index - links.size();
			for (int i = 0; i <=number; i++) {
				
			}
		}
	}
}
