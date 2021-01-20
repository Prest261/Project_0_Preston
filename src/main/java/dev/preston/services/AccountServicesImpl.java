package dev.preston.services;

import java.util.ArrayList;
import java.util.List;

import dev.preston.daos.AccountDAO;
import dev.preston.daos.AccountDAOlocalImpl;
import dev.preston.entities.Account;
import dev.preston.entities.User;

public class AccountServicesImpl implements AccountServices {
	
	private static AccountDAO adao = new AccountDAOlocalImpl();

	public Account createAccount(User userId, String acctName, double initialBalance) {
		Account account = new Account(userId.getUserId(), acctName, initialBalance);
		adao.createAccount(userId , account);
		System.out.println(acctName + " was created with a balance of " + initialBalance);
		return account;
	}

	public Account getAcctById(int id) {
		return adao.getAccountById(id);
	}

	public boolean depositToAccount(Account a, double depositAmount) {
		adao.getAccountById(a.getAcctId());
		double newBalance = a.getBalance() + depositAmount;
		System.out.println("You added " + depositAmount + " to " + a.getAcctName());
		System.out.println("Your new balance for " + a.getAcctName() + " account is " + newBalance);
		Account deposit = new Account(a.getAcctId(), newBalance);
		return adao.updateAccount(deposit);
	}

	public boolean withdrawFromAccount(Account a, double withdrawAmount) {
		adao.getAccountById(a.getAcctId());
		double newBalance = a.getBalance() - withdrawAmount;
		System.out.println("You withdrew " + withdrawAmount + " from " + a.getAcctName());
		System.out.println("Your new balance for " + a.getAcctName() + " account is " + newBalance);
		Account withdraw = new Account(a.getAcctId(), newBalance);
		return adao.updateAccount(withdraw);
	}

	public List<Account> viewAllUserAccounts(User user) {
		return adao.getAllAccounts(user);
		
	}

	public boolean deleteAccount(Account a) {
		adao.getAccountById(a.getAcctId());
		if (a.getBalance() == 0) {
			System.out.println("Your " + a.getAcctName() + " account has been deleted!");
			Account delete = new Account(a.getAcctId());
			return adao.deleteAccount(delete);
		} else if(a.getBalance() != 0){
			System.out.println("Cannot delete this account since it has a balance of " + a.getBalance());
		}
		return false;
	}

}
