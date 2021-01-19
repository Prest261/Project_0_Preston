package dev.preston.daos;

import java.util.List;

import dev.preston.entities.User;

//CRUD operations
public interface UserDAO {

	// CREATE new user
	public boolean createUser(User user);
	
	// READ 
	User getUserById(int id);
	User getUserByUsername(String username);
	
	public List<User> getAllUsers();
	
	// Update
	public boolean updateUser(User user, String firstName, String lastName);
	
	// Delete
	boolean deleteUser(User user);
	public List<User> deleteAllUsers();
}
