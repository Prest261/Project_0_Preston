package dev.preston.exceptions;

public class PasswordTooShortException extends Exception {

	int inputLength;
	
	public PasswordTooShortException(int length) {
		super("Password needs to be at least 8 characters!");
		this.inputLength = length;
	}
}
