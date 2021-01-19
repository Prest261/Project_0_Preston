package dev.preston.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.preston.entities.User;
import dev.preston.util.JDBCConnection;

public class UserDAOlocalImpl implements UserDAO {

	public static Connection conn = JDBCConnection.getConnection();

	public boolean createUser(User user) {
		try {

			String sql = "CALL add_user(?,?,?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, user.getFirstName());
			cs.setString(2, user.getLastName());
			cs.setString(3, user.getUsername());
			cs.setString(4, user.getPassword());
			cs.setString(5, Integer.toString(user.getIsSuperUser()));

			cs.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public User getUserById(int id) {
		try {

			String sql = "SELECT * FROM users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setIsSuperUser(rs.getInt("SUPER_USER"));

				return u;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateUser(User user, String firstName, String lastName) {

		try {

			String sql = "UPDATE users SET first_name = ?, last_name = ? WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ps.setString(3, Integer.toString(user.getUserId()));

			ps.executeQuery();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> getAllUsers() {

		List<User> users = new ArrayList<User>();

		try {

			String sql = "SELECT * FROM users";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setIsSuperUser(rs.getInt("SUPER_USER"));

				users.add(u);
			}

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUserByUsername(String username) {

		try {

			String sql = "SELECT * FROM users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setIsSuperUser(rs.getInt("SUPER_USER"));

				return u;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean deleteUser(User user) {
		try {

			String sql = "DELETE users WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(user.getUserId()));

			ps.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<User> deleteAllUsers() {

		List<User> users = new ArrayList<User>();

		try {

			String sql = "DELETE users;";
			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getInt("ID"));
				u.setFirstName(rs.getString("FIRST_NAME"));
				u.setLastName(rs.getString("LAST_NAME"));
				u.setUsername(rs.getString("USERNAME"));
				u.setPassword(rs.getString("PASSWORD"));
				u.setIsSuperUser(rs.getInt("SUPER_USER"));

				users.remove(u);
			}

			return users;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
