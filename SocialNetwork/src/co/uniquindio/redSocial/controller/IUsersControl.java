package co.uniquindio.redSocial.controller;

import co.uniquindio.redSocial.exceptions.NodeGraphNullException;
import co.uniquindio.redSocial.exceptions.NodeGraphWithLinksException;
import co.uniquindio.redSocial.exceptions.NodeRepeatException;
import co.uniquindio.redSocial.model.User;
import co.uniquindio.redSocial.util.Graph;

public interface IUsersControl {
	public void addUser(String id, String name, String surname, String nick_name, String email, String password,
			String image) throws NodeRepeatException;

	public User removeUser(String nick_name) throws NodeGraphWithLinksException, NodeGraphNullException;
	public Graph<User> getUsers();
	public void setUsers(Graph<User> users);



}
