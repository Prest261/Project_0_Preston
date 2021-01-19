package dev.preston.exceptions;

public class UsernameIncorrectLengthException extends Exception {

	private int inputLength;
	
	public UsernameIncorrectLengthException(int length) {
		super("Username must be at least 8 characters long");
		System.out.println();
	}

	public int getInputLength() {
		return inputLength;
	}

	public void setInputLength(int inputLength) {
		this.inputLength = inputLength;
	}
}
