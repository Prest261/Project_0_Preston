package dev.preston.exceptions;

public class ChararacterNotAllowedException extends Exception {

	String inputCharacter;
	public ChararacterNotAllowedException(String character) {
		super("Spaces are not allowed in the username");
		this.inputCharacter = character;
	}
}
