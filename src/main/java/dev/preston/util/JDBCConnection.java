package dev.preston.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JDBCConnection {

	private static Connection conn = null;

	public static Connection getConnection() {

		try {
			if (conn == null) {

				Class.forName("oracle.jdbc.driver.OracleDriver");
				
				Properties props = new Properties();
				FileInputStream input = new FileInputStream(JDBCConnection.class.getClassLoader().getResource("connection.properties1").getFile());
				props.load(input);
				
				String url = props.getProperty("url");
				String username = props.getProperty("username");
				String password = props.getProperty("password");
				
				conn = DriverManager.getConnection(url, username, password);
				
				return conn;
			} else {
				return conn;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
		
	}
	
	// testing connection --- do not keep in here
//	public static void main(String[] args) {
//		Connection conn1 = getConnection();
//		System.out.println(conn1);
//	}

}
