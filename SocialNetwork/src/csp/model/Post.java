package csp.model;

import java.io.Serializable;

import csp.exceptions.EmptyLinkedListException;
import csp.exceptions.IndexOutOfBoundsException;
import csp.exceptions.LikeNullException;
import csp.util.Date;
import csp.util.Node;
import csp.util.Queue;
import csp.util.Stack;

public class Post implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String comment;
	private Queue<Comment> comments;
	private Stack<Like> likes;
	private Stack<Love> loves;
	private Date datePost;
	
	private User userAssociated;

	/**
	 * Metodo constructor de la clase Post
	 */
	public Post() {
		comments = new Queue<Comment>();
		datePost = new Date();
		likes = new Stack<Like>();
		loves = new Stack<Love>();
	}

	/**
	 * Metodo constructor con parametros de la clase post
	 * 
	 * @param comment        contenido del post
	 * @param userAssociated usuario quien creo el post
	 */
	public Post(String comment, User userAssociated) {
		this();
		this.comment = comment;
		this.userAssociated = userAssociated;
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

	public User getUserAssociated() {
		return userAssociated;
	}

	public void setUserAssociated(User userAssociated) {
		this.userAssociated = userAssociated;
	}

	public Stack<Like> getLikes() {
		return likes;
	}

	public void setLikes(Stack<Like> likes) {
		this.likes = likes;
	}

	public Stack<Love> getLoves() {
		return loves;
	}

	public void setLoves(Stack<Love> loves) {
		this.loves = loves;
	}

	@Override
	public String toString() {
		String info = " Post: " + comment + " user: " + userAssociated.getName() + " likes: " + likes.toString()
				+ " loves: " + loves.toString();
		return info;
	}

}
