package dev.preston.entities;

public class User {

	private int userId = 0;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private int isSuperUser = 0;

	public User() {
		super();
	}

	public User(int superUser) {
		super();
		this.isSuperUser = superUser;
	}

	public User(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public User(String username, String password, int isSuper) {
		super();
		this.username = username;
		this.password = password;
		this.isSuperUser = isSuper;
	}

	public User(String firstName, String lastName, String username, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public User(int userId, String firstName, String lastName, String username, String password) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIsSuperUser() {
		return this.isSuperUser;
	}

	public void setIsSuperUser(int isSuperUser) {
		this.isSuperUser = isSuperUser;
	}

	@Override
	public String toString() {
		return String.format("%5d\t %10s\t %10s\t %10s\t %10s\t %10d\t", userId, firstName, lastName, username,
				password, isSuperUser);
	}

}
