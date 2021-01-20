package dev.preston.exceptions;

public class MustContainNumberException extends Exception {

	String inputChar;
	public MustContainNumberException(String character) {
		super("Password must contain at least 1 number");
		this.inputChar = character;
	}
}
