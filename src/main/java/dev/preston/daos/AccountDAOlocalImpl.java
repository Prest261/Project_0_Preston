package dev.preston.daos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.preston.entities.Account;
import dev.preston.entities.User;
import dev.preston.util.JDBCConnection;

public class AccountDAOlocalImpl implements AccountDAO {
	
	public static Connection conn = JDBCConnection.getConnection();

	public Account createAccount(User user, Account account) {
		
//		CREATE OR REPLACE PROCEDURE add_acct(user_id NUMBER, acct_name VARCHAR2, balance NUMBER)
//		INSERT INTO accounts VALUES (user_id, acct_seq.nextval, acct_name, balance);
		try {

			String sql = "CALL add_acct(?,?,?)";
			CallableStatement cs = conn.prepareCall(sql);
			
			cs.setString(1, Integer.toString(user.getUserId()));
			cs.setString(2, account.getAcctName());
			cs.setString(3, Double.toString(account.getBalance()));
			
			cs.execute();
			
			return account;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccountById(int id) {
		try {
			
			String sql = "SELECT * FROM accounts WHERE acct_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Account a = new Account();
				a.setAcctId(rs.getInt("ACCT_ID"));
				a.setUserId(rs.getInt("USER_ID"));
				a.setAcctName(rs.getString("ACCT_NAME"));
				a.setBalance(rs.getDouble("BALANCE"));
				
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Account getAccountByUserId(int userId) {
		try {
			
			String sql = "SELECT * FROM accounts WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Integer.toString(userId));
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				Account a = new Account();
				a.setAcctId(rs.getInt("ACCT_ID"));
				a.setUserId(rs.getInt("USER_ID"));
				a.setAcctName(rs.getString("ACCT_NAME"));
				a.setBalance(rs.getDouble("BALANCE"));
				
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Account> getAllAccounts(User user) {
		
		List<Account> accounts = new ArrayList<Account>(user.getUserId());
		
		try {
			
			String sql = "SELECT * FROM accounts WHERE user_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(user.getUserId()));
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account();
				a.setUserId(rs.getInt("USER_ID"));
				a.setAcctId(rs.getInt("ACCT_ID"));
				a.setAcctName(rs.getString("ACCT_NAME"));
				a.setBalance(rs.getInt("BALANCE"));
				
				accounts.add(a);
			}
			
			return accounts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateAccount(Account account) {
		try {
			
			String sql = "UPDATE accounts SET balance = ? WHERE acct_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, Double.toString(account.getBalance()));
			ps.setString(2, Integer.toString(account.getAcctId()));
			
			ps.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteAccount(Account account) {
		
		try {
			
			String sql = "DELETE accounts WHERE acct_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(account.getAcctId()));
			
			ps.executeQuery();
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
