package co.uniquindio.redSocial.model;

import co.uniquindio.redSocial.util.*;

public class Comment {
	private String message;
	private Queue<Comment> replies;
	private Stack<Like> likes;
	private Post postAssociated;
	/**
	 * Metodo constructor de la clase Comment
	 * @param message del Comment
	 */
	public Comment(String message, Post postAssociated) {
		this.message = message;
		replies = new Queue<Comment>();
		this.postAssociated = postAssociated;
		likes = new Stack<Like>();
	}
	/**
	 * Metodo constructor de la clase Comment
	 */
	public Comment() {
		this("Empty&%&%&%", new Post());
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Queue<Comment> getReplies() {
		return replies;
	}
	public void setReplies(Queue<Comment> replies) {
		this.replies = replies;
	}
	public Post getPostAssociated() {
		return postAssociated;
	}
	public void setPostAssociated(Post postAssociated) {
		this.postAssociated = postAssociated;
	}
	public Stack<Like> getLikes() {
		return likes;
	}
	public void setLikes(Stack<Like> likes) {
		this.likes = likes;
	}
}
