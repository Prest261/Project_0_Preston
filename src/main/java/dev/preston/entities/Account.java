package dev.preston.entities;

public class Account {

	private int userId;
	private int acctId = 0;
	private String acctName;
	private double balance;

	public Account() {
		super();
	}
	
	public Account(int id) {
		super();
		this.acctId = id;
	}

	public Account(String acctName, double balance) {
		super();
		this.acctName = acctName;
		this.balance = balance;
	}
	
	public Account(int acctId, double balance) {
		super();
		this.acctId = acctId;
		this.balance = balance;
	}
	
	public Account(int userId, String acctName, double balance) {
		super();
		this.userId = userId;
		this.acctName = acctName;
		this.balance = balance;
	}
	
	public Account(int userId, int acctId, String acctName, double balance) {
		super();
		this.userId = userId;
		this.acctId = acctId;
		this.acctName = acctName;
		this.balance = balance;
	}

	public int getUserId() {
		return this.userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getAcctName() {
		return this.acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAcctId() {
		return this.acctId;
	}
	
	public void setAcctId(int acctId) {
		this.acctId = acctId;
	}

	@Override
	public String toString() {
		return String.format("%5d\t %15d\t %15s\t %15.2f\t", userId, acctId, acctName, balance);
	}
	
}
