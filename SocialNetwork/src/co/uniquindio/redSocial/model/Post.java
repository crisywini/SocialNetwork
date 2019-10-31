package co.uniquindio.redSocial.model;

import java.io.Serializable;

import co.uniquindio.redSocial.exceptions.BigIndexException;
import co.uniquindio.redSocial.exceptions.EmptyLinkedListException;
import co.uniquindio.redSocial.util.*;

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

	/**
	 * Metodo que le permite a un usuario dar like
	 * 
	 * @param liker usuario que da like
	 * @throws EmptyLinkedListException de la clase {@link LinkedList}
	 * @throws BigIndexException        de la clase {@link Node}
	 */
	public void giveALike(User liker) throws BigIndexException, EmptyLinkedListException {
		Like newLike = new Like(liker);
		if (likes.contains(newLike)) {
			Love newLove = new Love(liker);
			if (!loves.contains(newLove))
				loves.push(newLove);
		} else
			likes.push(newLike);
	}

	@Override
	public String toString() {
		String info = " Post: " + comment + " user: " + userAssociated.getName() + " likes: " + likes.toString()
				+ " loves: " + loves.toString();
		return info;
	}

}
