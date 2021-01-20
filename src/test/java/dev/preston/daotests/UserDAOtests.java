package dev.preston.daotests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dev.preston.daos.UserDAO;
import dev.preston.daos.UserDAOlocalImpl;
import dev.preston.entities.User;

class UserDAOtests {
	private static UserDAO udao = new UserDAOlocalImpl();

	@Test
	public void addUser() {
		User u = new User("Sydney", "Preston", "preston310", "password1");
		System.out.println(u);
		udao.createUser(u);
		System.out.println(u);
	}
	
	@Test
	public void updateUser() {
		User u = new User("Sydney", "Preston");
		udao.updateUser(u, "Syd", "P");
		System.out.println(u);
	}

}
