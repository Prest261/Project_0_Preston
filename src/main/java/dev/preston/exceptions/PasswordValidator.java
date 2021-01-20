package dev.preston.exceptions;

public interface PasswordValidator {

	boolean validPassword(String password) throws MustContainNumberException, PasswordTooShortException;
}
