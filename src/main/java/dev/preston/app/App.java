package dev.preston.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.preston.entities.Account;
import dev.preston.entities.User;
import dev.preston.exceptions.ChararacterNotAllowedException;
import dev.preston.exceptions.MustContainNumberException;
import dev.preston.exceptions.PasswordTooShortException;
import dev.preston.exceptions.PasswordValidator;
import dev.preston.exceptions.PasswordValidatorImpl;
import dev.preston.exceptions.UsernameIncorrectLengthException;
import dev.preston.exceptions.UsernameValidator;
import dev.preston.exceptions.UsernameValidatorImpl;
import dev.preston.services.AccountServices;
import dev.preston.services.AccountServicesImpl;
import dev.preston.services.UserServices;
import dev.preston.services.UserServicesImpl;

public class App {

	private static Scanner scan = new Scanner(System.in);
	private static UserServices userv = new UserServicesImpl();
	private static AccountServices aserv = new AccountServicesImpl();
	private static UsernameValidator validator = new UsernameValidatorImpl();
	private static PasswordValidator pValidator = new PasswordValidatorImpl();
	private static User loggedInUser = null;
	private static List<Account> loggedInUserAccounts = null;

	public static void main(String[] args) {

		// boolean isValid;

		while (true) {
			boolean loggedIn = false;

			System.out.println("Welcome to the Bank of Wreath! Please sign in or register as a new user.");
			System.out.println("1\t Sign in");
			System.out.println("2\t Register as a new user");

			int choice = Integer.parseInt(scan.nextLine());

			switch (choice) {
			case 1: {
				System.out.println("Please enter your Username");
				String username = scan.nextLine();
				System.out.println("Please enter your Password");
				String password = scan.nextLine();
				loggedInUser = userv.login(username, password);
				// System.out.println(loggedInUser);
				loggedIn = true;

			}
				break;

			case 2: {
				System.out.println("Enter a minimum 8 character username with no spaces");
				String username = scan.nextLine();
				isUsernameValid(username);
				System.out.println("Enter a minimum 8 character password with at least one number");
				String password = scan.nextLine();
				isPasswordValid(password);
				System.out.println("Enter your first name");
				String firstName = scan.nextLine();
				System.out.println("Enter your last name");
				String lastName = scan.nextLine();
				userv.registerNewUser(firstName, lastName, username, password);
				User u = userv.getUserByUsername(username);
				System.out.println(u);
				System.out.println("Registration successful " + firstName + " " + lastName + "!");
			}
				break;
			}

			while (loggedInUser != null) {
				// LoggedInMenu(loggedInUser);
				System.out.println("Please choose what you would like to do next");
				System.out.println("1\t Create new Account");
				System.out.println("2\t View all Accounts");
				System.out.println("3\t Deposit to an Account");
				System.out.println("4\t Withdraw from an Account");
				System.out.println("5\t Delete an Account");
				System.out.println("6\t Log out");
				System.out.println("7\t View Super User Menu");
				int choice2 = Integer.parseInt(scan.nextLine());
				// User user = new User();

				switch (choice2) {
				case 1: {

					System.out.println("Please enter a name for the Account you would like to create");
					String acctName = scan.nextLine();
					System.out.println("Please enter the initial amount you wish to deposit into the account.");
					Double initialBalance = Double.parseDouble(scan.nextLine());
					User currentUser = new User(loggedInUser.getUserId(), loggedInUser.getFirstName(),
							loggedInUser.getLastName(), loggedInUser.getUsername(), loggedInUser.getPassword());
					User id = userv.getUserById(currentUser.getUserId());
					Account acct = aserv.createAccount(id, acctName, initialBalance);
				}
					break;

				case 2: {
					formatAccounts();
//					System.out.println("Here are all of your accounts:");
//					List<Account> accounts = aserv.viewAllUserAccounts(loggedInUser);
//					System.out.println(accounts);
				}
					break;

				case 3: {
					System.out.println(aserv.viewAllUserAccounts(loggedInUser));
					System.out.println("Please enter the Account Id you would like to deposit into");
					int aId = Integer.parseInt(scan.nextLine());
					// create a loop to check all the accounts and see if any of the names match
					// with the names of accounts
					// if matches create (boolean accountFound = true) then deposit to account
					for (Account a : aserv.viewAllUserAccounts(loggedInUser)) {
						if (aId == a.getAcctId()) {
							System.out.println("Please enter deposit amount");
							double depositAmount = Double.parseDouble(scan.nextLine());
							Account acctId = aserv.getAcctById(a.getAcctId());
							aserv.depositToAccount(acctId, depositAmount);
						}
					}
				}
					break;

				case 4: {
					System.out.println(aserv.viewAllUserAccounts(loggedInUser));
					System.out.println("Please enter the Account Id you would like to withdraw from");
					int aId = Integer.parseInt(scan.nextLine());
					for (Account a : aserv.viewAllUserAccounts(loggedInUser)) {
						if (aId == a.getAcctId()) {
							System.out.println("Please enter the amount you would like to withdraw");
							double withdrawAmount = Double.parseDouble(scan.nextLine());
							Account acctId = aserv.getAcctById(a.getAcctId());
							aserv.withdrawFromAccount(acctId, withdrawAmount);
						}
					}
				}
					break;

				case 5: {
					System.out.println(
							"You may only delete an account that has a balance of zero.");
					formatAccounts();
					System.out.println("Please enter the Account Id of the account you wish to delete");
					int aId = Integer.parseInt(scan.nextLine());
					for (Account a : aserv.viewAllUserAccounts(loggedInUser)) {
						if (aId == a.getAcctId()) {
							System.out.println("Are you sure you want to delete " + a.getAcctName() + " account?");
							System.out.println("Please press 1 to delete the account");
							int delete = Integer.parseInt(scan.nextLine());
							if (delete == 1) {
								Account acctId = aserv.getAcctById(a.getAcctId());
								System.out.println("Your " + a.getAcctName() + " account has been deleted!");
								aserv.deleteAccount(acctId);
							}
						}
					}
				}
					break;
				case 6: {
					loggedInUser = null;
				}
					break;

				case 7: {
					User superUser = new User(loggedInUser.getIsSuperUser());
					if (superUser.getIsSuperUser() == 1) {
						superUserMenu();
					}
				}
					break;
				}

			}
		}
	}

	public static void formatUsers() {
		System.out.println("Here are all of the Users:");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.printf("%7s\t %10s\t %10s\t %10s\t %10s\t %10s\t \n", "Id |", " First Name |", " Last Name |",
				" Username |", " Password |", " Super User");
		System.out.println("---------------------------------------------------------------------------------------");
		for (User u : userv.getAllUsers()) {
			System.out.println(u);
			System.out
					.println("---------------------------------------------------------------------------------------");
		}
	}
	
	public static void formatAccounts() {
		System.out.println("Here are all of the Accounts");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.printf("%5s\t %5s\t %8s\t %8s\t \n", " User ID |", "Account ID |", "Account Name |", "Balance");
		System.out.println("-----------------------------------------------------------------------------");
		for (Account a : aserv.viewAllUserAccounts(loggedInUser) ) {
			System.out.println(a);
			System.out
					.println("-----------------------------------------------------------------------------");
		}
	}

	// break it up into methods for the various menus?
	public static void isUsernameValid(String username) {
		boolean isValid;

		do {

			try {
				isValid = validator.validUsername(username);
				System.out.println("Username created successfully!");
				isValid = true;
			} catch (ChararacterNotAllowedException e) {
				System.out.println("You used an inappropriate character");
				isValid = false;
			} catch (UsernameIncorrectLengthException e) {
				System.out.println("Your username was too short");
				System.out.println(e.getMessage() + " Your username was: " + e.getInputLength() + " characters");
				isValid = false;
			}
		} while (isValid = false);
	}

	public static void isPasswordValid(String password) {
		boolean isValid;

		do {

			try {
				isValid = pValidator.validPassword(password);
				System.out.println("Password created successfully!");
				isValid = true;
			} catch (MustContainNumberException e) {
				System.out.println("Password must contain a number");
				isValid = false;
			} catch (PasswordTooShortException e) {
				System.out.println("Your password was too short");
				isValid = false;
			}
		} while (isValid = false);
	}

	public static void superUserMenu() {
		boolean isSuper;
		while (isSuper = true) {
			System.out.println("Welcome super user! What would you like to do?");
			System.out.println("1\t Create new User");
			System.out.println("2\t View all Users");
			System.out.println("3\t Update a User");
			System.out.println("4\t Delete a User");
			System.out.println("5\t Logout");
			int choice3 = Integer.parseInt(scan.nextLine());

			switch (choice3) {
			case 1: {
				System.out.println("Enter a minimum 8 character username with no spaces");
				String username = scan.nextLine();
				isUsernameValid(username);
				System.out.println("Enter a minimum 8 character password with at least one number");
				String password = scan.nextLine();
				isPasswordValid(password);
				System.out.println("Enter the first name of the user");
				String firstName = scan.nextLine();
				System.out.println("Enter last name of the user");
				String lastName = scan.nextLine();
				userv.registerNewUser(firstName, lastName, username, password);
				User u = userv.getUserByUsername(username);
				System.out.println(u);
				System.out.println("You successfully created a user for " + firstName + " " + lastName + "!");
			}
				break;

			case 2: {
				formatUsers();
			}
				break;

			case 3: {
				System.out.println("You can update the first and last name for a user");
				formatUsers();
				System.out.println("Please enter the Id of the user you wish to update");
				int uId = Integer.parseInt(scan.nextLine());
				for (User u : userv.getAllUsers()) {
					if (uId == u.getUserId()) {
						System.out.println("Please enter new first name");
						String firstName = scan.nextLine();
						System.out.println("Please enter new last name");
						String lastName = scan.nextLine();
						User updateUser = userv.getUserById(u.getUserId());
						userv.updateUser(updateUser, firstName, lastName);
					}

				}

			}
				break;

			case 4: {
				formatUsers();
				System.out.println("Please enter the Id of the user you want to delete");
				int uId = Integer.parseInt(scan.nextLine());
				for (User u : userv.getAllUsers()) {
					if (uId == u.getUserId()) {
						User deleteUser = userv.getUserById(u.getUserId());
						userv.deleteUser(deleteUser);
					}
				}

			}
				break;

			case 5: {
				loggedInUser = null;
				return;
			}
			}
		}
	}
}
