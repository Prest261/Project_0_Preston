package dev.preston.services;

import java.util.List;

import dev.preston.entities.User;

public interface UserServices {
	
	// used to register new user
	public boolean registerNewUser(String firstName, String lastName, String username, String password);
	
	public List<User> getAllUsers();
	public User getUserByUsername(String username);
	
	public User getUserById(int userId);
	
	User login(String username, String password);
	
	// update methods for super user
	public boolean updateUser(User userId, String firstName, String lastName);
	
	// delete methods for super user
	public boolean deleteUser(User user);
	public List<User> deleteAllUsers();
	

}
