package csp.model;

import java.io.Serializable;

import csp.util.Date;
import csp.util.Queue;

public class Post implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comment;
	private Queue<Comment> comments;
	private Date datePost;
	private int counterLikes;
	/**
	 * Metodo constructor de la clase Post
	 */
	public Post() {
		comments = new Queue<Comment>();
		datePost = new Date();	
		setCounterLikes(0);
	}

	public Queue<Comment> getComments() {
		return comments;
	}

	public void setComments(Queue<Comment> comments) {
		this.comments = comments;
	}

	public Date getDatePost() {
		return datePost;
	}

	public void setDatePost(Date datePost) {
		this.datePost = datePost;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCounterLikes() {
		return counterLikes;
	}

	public void setCounterLikes(int counterLikes) {
		this.counterLikes = counterLikes;
	}
}
