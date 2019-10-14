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

	/**
	 * Mejorar metodo
	 * 
	 * @param liker
	 * @throws IndexOutOfBoundsException
	 * @throws EmptyLinkedListException
	 * @throws LikeNullException 
	 */
	public void giveALike(User liker) throws EmptyLinkedListException, IndexOutOfBoundsException{
		System.out.println(isPostLike(liker));
		System.out.println(isPostLove(liker));
		if (isPostLike(liker)) {
			if (!isPostLove(liker))
			{
				
			}
		} else
			likes.push(new Like(liker));
	}

	/**
	 * Metodo que permite verificar si un usuario dio like
	 * 
	 * @param idUser del user
	 * @return un boolean verificando si el user dio like o no
	 * @throws IndexOutOfBoundsException
	 * @throws EmptyLinkedListException
	 * @throws LikeNullException 
	 */
	public boolean isPostLike(User user) throws EmptyLinkedListException, IndexOutOfBoundsException{
		if(likes.isEmpty())
			return false;
		Node<Like> likeAuxiliar;
		try
		{
			likeAuxiliar = new Node<Like>(getLike(user));
		}
		catch (LikeNullException likeNull) {
			return false;
		}
 		return likes.contains(likeAuxiliar);
	}
	/**
	 * Metodo que permite obtener un like dado un usuario 
	 * @param user
	 * @return
	 * @throws EmptyLinkedListException
	 * @throws LikeNullException
	 */
	public Like getLike(User user) throws EmptyLinkedListException, LikeNullException
	{
		if(likes.isEmpty())
			throw new EmptyLinkedListException("There are not likes");
		Node<Like> auxiliar = likes.peek();
		Like like = null;
		while (auxiliar != null) {
			if (auxiliar.getSizelinks() == 0)
				auxiliar = null;
			else
				try {
					auxiliar = auxiliar.followLink(0);
					if(auxiliar.getValue().getId().equals(user.getId()))
						like = auxiliar.getValue();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
		}
		if(like == null)
			throw new LikeNullException("The like does not exist");
		return like;
	}
	public Love getLove(User user) throws EmptyLinkedListException, LikeNullException
	{
		if(loves.isEmpty())
			throw new EmptyLinkedListException("There are not likes");
		Node<Love> auxiliar = loves.peek();
		Love love = null;
		while (auxiliar != null) {
			if (auxiliar.getSizelinks() == 0)
				auxiliar = null;
			else
				try {
					auxiliar = auxiliar.followLink(0);
					if(auxiliar.getValue().getId().equals(user.getId()))
						love = auxiliar.getValue();
				} catch (IndexOutOfBoundsException e) {
					e.printStackTrace();
				}
		}
		if(love == null)
			throw new LikeNullException("The like does not exist");
		return love;
		
		
	}

	/**
	 * Metodo que verifica si el usuario que le dio like 2 veces ya se encuentra en
	 * loves
	 * 
	 * @param idUser del user
	 * @return un booleano con la verificacion del love
	 * @throws IndexOutOfBoundsException
	 * @throws EmptyLinkedListException
	 * @throws LikeNullException 
	 */
	public boolean isPostLove(User user) throws EmptyLinkedListException, IndexOutOfBoundsException{
		if(likes.isEmpty())
			throw new EmptyLinkedListException("There are not loves");
		Node<Love> loveAuxiliar;
		try {
			loveAuxiliar = new Node<Love>(getLove(user));
			
		} catch (LikeNullException loveNullException) {
			return false;
		}
 		return loves.contains(loveAuxiliar);
	}

	@Override
	public String toString() {
		String info = " Post: " + comment + " user: " + userAssociated.getName() + " likes: " + likes.toString()
				+ " loves: " + loves.toString();
		return info;
	}

}
