package csp.model;

import csp.util.Queue;

public class Comment {
	private String message;
	private int counterLikes;
	private Queue<Comment> replies;
	private Post postAssociated;
	/**
	 * Metodo constructor de la clase Comment
	 * @param message del Comment
	 */
	public Comment(String message, Post postAssociated) {
		this.message = message;
		counterLikes = 0;
		replies = new Queue<Comment>();
		this.postAssociated = postAssociated;
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
	public int getCounterLikes() {
		return counterLikes;
	}
	public void setCounterLikes(int counterLikes) {
		this.counterLikes = counterLikes;
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
}
