package dev.preston.daos;

import java.util.List;

import dev.preston.entities.Account;
import dev.preston.entities.User;

public interface AccountDAO {

	// CREATE new account
	public Account createAccount(User user, Account account);
	
	// to get one account
	Account getAccountById(int id);
	Account getAccountByUserId(int userId);
	
	public List<Account> getAllAccounts(User user);
	
	// UPDATE METHODS
	public boolean updateAccount(Account account);
	
	// Delete account if empty
	public boolean deleteAccount(Account account);
}
