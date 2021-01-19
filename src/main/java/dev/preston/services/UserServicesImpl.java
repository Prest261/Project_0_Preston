package dev.preston.services;

import java.util.List;

import dev.preston.daos.UserDAO;
import dev.preston.daos.UserDAOlocalImpl;
import dev.preston.entities.User;

public class UserServicesImpl implements UserServices {
	
	private static UserDAO udao = new UserDAOlocalImpl();

	public boolean registerNewUser(String firstName, String lastName, String username, String password) {
		User user = new User(firstName, lastName, username, password);
		udao.createUser(user);
		return true;
	}

	public User login(String username, String password) {
		
		for(User u : udao.getAllUsers()) {
			if(u.getUsername().equals(username) && u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}

	public User getUserByUsername(String username) {
		return udao.getUserByUsername(username);
	}

	public User getUserById(int userId) {
		return udao.getUserById(userId);
	}

	public boolean updateUser(User user, String firstName, String lastName) {
		return udao.updateUser(user, firstName, lastName);
	}

	public boolean deleteUser(User user) {
		return udao.deleteUser(user);
	}

	public List<User> deleteAllUsers() {
		return udao.deleteAllUsers();
	}

	public List<User> getAllUsers() {
		return udao.getAllUsers();
	}

}
