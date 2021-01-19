package dev.preston.services;

import java.util.ArrayList;
import java.util.List;

import dev.preston.entities.Account;
import dev.preston.entities.User;

public interface AccountServices {

	// create accounts
	public Account createAccount(User userId, String acctName, double initialBalance);
	
	// view all accounts
	//List<Account> viewAllAccounts();
	
	// view all user accounts
	public Account getAcctById(int id);
	
	// Deposit to acct
	//Account depositToAccount(int id, String acctName, double depositAmount);
	
	public boolean depositToAccount(Account a, double depositAmount);
		
	// withdraw from acct
	// if deposit works - structure the same way
	public boolean withdrawFromAccount(Account a, double withdrawAmount);

	//public Account viewAllUserAccounts(int userId);
	
	public List<Account> viewAllUserAccounts(User user);
	
	public boolean deleteAccount(Account a);
}
